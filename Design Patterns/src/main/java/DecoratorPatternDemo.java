// ===== Base Component =====
interface TextProcessor {
    String process(String text);
}

// ===== Concrete Component =====
class PlainText implements TextProcessor {
    @Override
    public String process(String text) {
        return text; // Returns the original text
    }
}

// ===== Base Decorator =====
abstract class TextDecorator implements TextProcessor {
    protected TextProcessor wrappee;

    public TextDecorator(TextProcessor wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public String process(String text) {
        return wrappee.process(text);
    }
}

// ===== Concrete Decorators =====
class EncryptionDecorator extends TextDecorator {
    public EncryptionDecorator(TextProcessor wrappee) {
        super(wrappee);
    }

    @Override
    public String process(String text) {
        String processed = super.process(text);
        // Simple encryption: reverse string (for demo)
        return new StringBuilder(processed).reverse().toString();
    }
}

class CompressionDecorator extends TextDecorator {
    public CompressionDecorator(TextProcessor wrappee) {
        super(wrappee);
    }

    @Override
    public String process(String text) {
        String processed = super.process(text);
        // Simple compression: remove spaces (for demo)
        return processed.replaceAll("\\s+", "");
    }
}

// ===== Main Class =====
public class DecoratorPatternDemo {
    public static void main(String[] args) {
        String originalText = "Hello World from Decorator";

        // Apply Encryption then Compression
        TextProcessor processor1 = new CompressionDecorator(
                new EncryptionDecorator(
                        new PlainText()));
        System.out.println("Encryption -> Compression: " + processor1.process(originalText));

        // Apply Compression then Encryption
        TextProcessor processor2 = new EncryptionDecorator(
                new CompressionDecorator(
                        new PlainText()));
        System.out.println("Compression -> Encryption: " + processor2.process(originalText));
    }
}
