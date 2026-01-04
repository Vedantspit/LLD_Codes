class Payment {
  process(amount) {
    throw new Error("Implement for the payment class needed");
  }
}

class StripeAdapter extends Payment {
  process(amount) {
    console.log(`[Stripe] Charging $${amount}... Done.`);
    return true;
  }
}
class PayPalAdapter extends Payment {
  process(amount) {
    console.log(`[PayPal] Charging $${amount}... Done.`);
    return true;
  }
}
module.exports = { StripeAdapter, PayPalAdapter };
