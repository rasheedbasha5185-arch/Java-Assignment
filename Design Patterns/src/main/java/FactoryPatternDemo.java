// ===== Payment Gateway Interface =====
interface PaymentGateway {
    void pay(double amount);
}

// ===== Concrete Gateways =====
class PayPal implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal");
    }
}

class Stripe implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Stripe");
    }
}

class RazorPay implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using RazorPay");
    }
}

// ===== Factory Class =====
class PaymentFactory {
    public static PaymentGateway getPaymentGateway(String type) {
        if ("PayPal".equalsIgnoreCase(type)) {
            return new PayPal();
        } else if ("Stripe".equalsIgnoreCase(type)) {
            return new Stripe();
        } else if ("RazorPay".equalsIgnoreCase(type)) {
            return new RazorPay();
        } else {
            throw new IllegalArgumentException("Unknown payment gateway: " + type);
        }
    }
}

// ===== Main Class =====
public class FactoryPatternDemo {
    public static void main(String[] args) {
        PaymentGateway gateway1 = PaymentFactory.getPaymentGateway("PayPal");
        gateway1.pay(100);

        PaymentGateway gateway2 = PaymentFactory.getPaymentGateway("Stripe");
        gateway2.pay(200);

        PaymentGateway gateway3 = PaymentFactory.getPaymentGateway("RazorPay");
        gateway3.pay(300);
    }
}
