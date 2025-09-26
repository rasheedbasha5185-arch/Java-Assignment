// ===== Product Class =====
class Car {
    private String engine;
    private int wheels;
    private String color;
    private boolean sunroof;
    private boolean GPS;

    // Private constructor to enforce builder usage
    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.color = builder.color;
        this.sunroof = builder.sunroof;
        this.GPS = builder.GPS;
    }

    @Override
    public String toString() {
        return "Car [engine=" + engine + ", wheels=" + wheels + ", color=" + color +
                ", sunroof=" + sunroof + ", GPS=" + GPS + "]";
    }

    // ===== Builder Class =====
    public static class CarBuilder {
        private String engine;
        private int wheels;
        private String color;
        private boolean sunroof;
        private boolean GPS;

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setWheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder setSunroof(boolean sunroof) {
            this.sunroof = sunroof;
            return this;
        }

        public CarBuilder setGPS(boolean GPS) {
            this.GPS = GPS;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

// ===== Main Class =====
public class BuilderPatternDemo {
    public static void main(String[] args) {
        // Building a car step by step
        Car car = new Car.CarBuilder()
                .setEngine("V8")
                .setWheels(4)
                .setColor("Red")
                .setSunroof(true)
                .setGPS(true)
                .build();

        System.out.println(car);

        // Building a simpler car
        Car basicCar = new Car.CarBuilder()
                .setEngine("V4")
                .setWheels(4)
                .setColor("Blue")
                .build();

        System.out.println(basicCar);
    }
}
