const { Vehicle, ParkingSlot } = require("./models.js");
const {
  StripeAdapter,
  PayPalAdapter,
  PricingRule,
  PaymentProcessor,
} = require("./strategies.js");
const { ParkingManager } = require("./parkingService.js");

async function run() {
  const floor1 = {
    id: 1,
    slots: [new ParkingSlot("S1", "CAR", 1), new ParkingSlot("S2", "BIKE", 1)],
  };
  const pricing = new PricingRule();
  const gateway = new StripeAdapter();

  const payment = new PaymentProcessor(gateway);
  const pms = new ParkingManager([floor1], pricing, payment);
  const myCar = new Vehicle("V1", "MH-01-1234", "CAR");
  const entryRes = pms.entryVehicle(myCar);
  if (entryRes.success) {
    const exitRes = await pms.exitVehicle(entryRes.data.id);
    console.log("\n Exit result ", exitRes.success ? " Success" : "Failed");
  }
}
run();
