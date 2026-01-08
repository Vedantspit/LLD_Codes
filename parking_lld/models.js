class Vehicle {
  constructor(id, licensePlate, type) {
    this.id = id;
    this.licensePlate = licensePlate;
    this.type = type;
  }
}

class ParkingSlot {
  constructor(id, slotType, floorNumber) {
    this.id = id;
    this.slotType = slotType;
    this.floorNumber = floorNumber;
    this.isOccupied = false;
    this.vehicle = null;
  }
  assign(vehicle) {
    this.vehicle = vehicle;
    this.isOccupied = true;
  }
  release() {
    this.isOccupied = false;
    this.vehicle = null;
  }
}

class Ticket {
  constructor(vehicle, slotId) {
    this.id = `TKT-${Date.now()}`;
    this.vehicleId = vehicle.id;
    this.vehicleType = vehicle.type;
    this.slotId = slotId;
    this.entryTime = new Date(Date.now() - 3 * 60 * 60 * 1000);
    this.isActive = true;
  }
}

class Receipt {
  constructor(ticketId, amount) {
    this.id = `RCP-${Date.now()}`;
    this.ticketId = ticketId;
    this.amount = amount;
    this.exitTime = new Date();
  }
}
module.exports = { Vehicle, ParkingSlot, Ticket, Receipt };
