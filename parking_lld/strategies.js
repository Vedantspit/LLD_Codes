class PaymentAdapter {
  async pay(amount) {
    throw new Error("Method pay() need to be implemented");
  }
}

class StripeAdapter extends PaymentAdapter {
  async pay(amount) {
    console.log(`[Stripe API] Charging $${amount}...`);
    return Math.random() < 0.1;
  }
}
class PayPalAdapter extends PaymentAdapter {
  async pay(amount) {
    console.log(`[PayPal API] Charging $${amount}...`);
    return Math.random() > 0.1;
  }
}
class PricingRule {
  calculate(entryTime, type) {
    const hours = Math.ceil((new Date() - entryTime) / (1000 * 60 * 60));
    const rate = type === "CAR" ? 20 : 10;
    return hours * rate;
  }
}
class PaymentProcessor {
  constructor(adapter) {
    this.adapter = adapter;
  }
  async payWithRetry(amount, retries = 3) {
    for (let i = 1; i <= retries; i++) {
      try {
        console.log(
          `[Payment] Attempt ${i} using ${this.adapter.constructor.name}`
        );
        const success = await this.adapter.pay(amount);
        if (success) return true;
        throw new Error("Gateway Rejected Payment");
      } catch (err) {
        if (i == retries) {
          throw err;
        }
        console.log(`Retrying due to: ${err.message}`);
      }
    }
  }
}
module.exports = {
  PricingRule,
  PaymentProcessor,
  StripeAdapter,
  PayPalAdapter,
};
