
public class Main {
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("no input");
            return;
        }

        switch (args[1]) {
            case ("-add"): {
                System.out.println("add!");
                break;
            }
            case ("-rm"): {
                System.out.println("rm!");
                break;
            }
            case ("-show"): {
                System.out.println("show!");
                break;
            }
            default:
                System.out.println("invalid input format");
        }
    }
}
