const { Ticket, Receipt } = require("./models.js");

class ParkingManager {
  constructor(floors, pricingRule, paymentProcessor) {
    this.floors = floors;
    this.pricingRule = pricingRule;
    this.paymentProcessor = paymentProcessor;
    this.activeTickets = new Map();
  }
  entryVehicle(vehicle) {
    console.log(`\n[Entry] Vehicle ${vehicle.licensePlate} arrived`);
    let allocatedSlot = null;
    for (const floor of this.floors) {
      allocatedSlot = floor.slots.find(
        (s) => !s.isOccupied && s.slotType === vehicle.type
      );
      if (allocatedSlot) break;
    }

    if (!allocatedSlot) {
      console.log("Response: Parking Full!");
      return { success: false, message: "No slots available" };
    }

    allocatedSlot.assign(vehicle);
    const ticket = new Ticket(vehicle, allocatedSlot.id);
    this.activeTickets.set(ticket.id, ticket);
    console.log(
      `Response: Ticket generated ${ticket.id} at Slot ${allocatedSlot.id}`
    );
    return { success: true, data: ticket };
  }

  async exitVehicle(ticketId) {
    console.log(`\n[Exit] Processing Ticket: ${ticketId}`);
    const ticket = this.activeTickets.get(ticketId);
    if (!ticket || !ticket.isActive) {
      return { success: false, message: "Invalid Ticket" };
    }
    const amount = this.pricingRule.calculate(
      ticket.entryTime,
      ticket.vehicleType
    );
    console.log(
      `Generating Reciept for Ticket ID-${ticket.id} with amount- $ ${amount}`
    );

    try {
      await this.paymentProcessor.payWithRetry(amount);
      const slot = this.findSlotById(ticket.slotId);
      if (slot) slot.release();
      ticket.isActive = false;
      this.activeTickets.delete(ticketId);
      const receipt = new Receipt(ticketId, amount);

      console.log(
        `Response : Reciept Generated[Receiptid ${receipt.id}] for Ticket ID-${ticket.id} with amount- $ ${amount}. \n
        Slot ${slot.id} is now free`
      );
      return { success: true, data: receipt };
    } catch (error) {
      return { success: false, message: "Payment Failed after retries" };
    }
  }

  findSlotById(slotId) {
    for (const floor of this.floors) {
      const slot = floor.slots.find((s) => s.id === slotId);
      if (slot) return slot;
    }
  }
}

module.exports = { ParkingManager };
