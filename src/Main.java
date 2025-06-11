import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ContainerShip> ships = new ArrayList<>();
        ArrayList<Container> containers = new ArrayList<>();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Ship");
            System.out.println("2. Remove Ship");
            System.out.println("3. Add Container");
            System.out.println("4. Load Cargo");
            System.out.println("5. Put Container on Ship");
            System.out.println("6. Unload Container");
            System.out.println("7. Replace Container");
            System.out.println("8. Move Container to Another Ship");
            System.out.println("9. Print Container Info");
            System.out.println("10. Print Ship Info");
            System.out.println("0. Exit");

            System.out.print("Your choice: ");
            int ch = Integer.parseInt(sc.nextLine());

            if (ch == 1) {
                System.out.println("Ship name?");
                String n = sc.nextLine();
                System.out.println("Max speed?");
                double spd = Double.parseDouble(sc.nextLine());
                System.out.println("Max containers?");
                int mc = Integer.parseInt(sc.nextLine());
                System.out.println("Max weight?");
                double mw = Double.parseDouble(sc.nextLine());
                ships.add(new ContainerShip(n, spd, mc, mw));
                System.out.println("Ship added!");
            } else if (ch == 2) {
                for (int i = 0; i < ships.size(); i++) {
                    System.out.println(i + ": " + ships.get(i).name);
                }
                System.out.println("Index to remove?");
                int r = Integer.parseInt(sc.nextLine());
                if (r >= 0 && r < ships.size()) {
                    ships.remove(r);
                    System.out.println("Ship removed!");
                } else {
                    System.out.println("Bad index.");
                }
            } else if (ch == 3) {
                System.out.println("Type? (1=Liquid, 2=Gas, 3=Fridge)");
                int type = Integer.parseInt(sc.nextLine());
                System.out.println("Height?");
                double h = Double.parseDouble(sc.nextLine());
                System.out.println("Tare?");
                double t = Double.parseDouble(sc.nextLine());
                System.out.println("Depth?");
                double d = Double.parseDouble(sc.nextLine());
                System.out.println("Capacity?");
                double cap = Double.parseDouble(sc.nextLine());

                if (type == 1) {
                    System.out.println("Hazardous? (true/false)");
                    boolean hz = Boolean.parseBoolean(sc.nextLine());
                    containers.add(new LiquidContainer(h, t, d, cap, hz));
                } else if (type == 2) {
                    System.out.println("Pressure?");
                    double p = Double.parseDouble(sc.nextLine());
                    containers.add(new GasContainer(h, t, d, cap, p));
                } else if (type == 3) {
                    System.out.println("Product?");
                    String prod = sc.nextLine();
                    System.out.println("Current temp?");
                    double tmp = Double.parseDouble(sc.nextLine());
                    System.out.println("Min temp?");
                    double min = Double.parseDouble(sc.nextLine());
                    containers.add(new RefrigeratedContainer(h, t, d, cap, prod, tmp, min));
                } else {
                    System.out.println("Wut?");
                }
            } else if (ch == 4) {
                if (containers.size() == 0) {
                    System.out.println("No containers :(");
                } else {
                    for (int i = 0; i < containers.size(); i++) {
                        System.out.println(i + ": " + containers.get(i).getSerialNumber());
                    }
                    System.out.println("Pick one:");
                    int c = Integer.parseInt(sc.nextLine());
                    if (c >= 0 && c < containers.size()) {
                        System.out.println("How much cargo?");
                        double m = Double.parseDouble(sc.nextLine());
                        try {
                            containers.get(c).loadCargo(m);
                            System.out.println("Cargo loaded!");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            } else if (ch == 5) {
                if (ships.size() == 0 || containers.size() == 0) {
                    System.out.println("Add ships/containers first!");
                } else {
                    for (int i = 0; i < ships.size(); i++) {
                        System.out.println(i + ": " + ships.get(i).name);
                    }
                    int s = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < containers.size(); i++) {
                        System.out.println(i + ": " + containers.get(i).getSerialNumber());
                    }
                    int c = Integer.parseInt(sc.nextLine());
                    if (ships.get(s).addContainer(containers.get(c))) {
                        containers.remove(c);
                        System.out.println("Container boarded!");
                    } else {
                        System.out.println("Too heavy");
                    }
                }
            } else if (ch == 6) {
                if (ships.size() == 0) {
                    System.out.println("No ships.");
                } else {
                    for (int i = 0; i < ships.size(); i++) {
                        System.out.println(i + ": " + ships.get(i).name);
                    }
                    int s = Integer.parseInt(sc.nextLine());
                    System.out.println("Serial to remove?");
                    String sn = sc.nextLine();
                    ships.get(s).removeContainer(sn);
                }
            } else if (ch == 7) {
                System.out.println("Which ship?");
                for (int i = 0; i < ships.size(); i++) {
                    System.out.println(i + ": " + ships.get(i).name);
                }
                int s = Integer.parseInt(sc.nextLine());
                System.out.println("Serial to replace?");
                String oldOne = sc.nextLine();
                System.out.println("Which container?");
                for (int i = 0; i < containers.size(); i++) {
                    System.out.println(i + ": " + containers.get(i).getSerialNumber());
                }
                int newIndex = Integer.parseInt(sc.nextLine());
                ships.get(s).removeContainer(oldOne);
                ships.get(s).addContainer(containers.get(newIndex));
                containers.remove(newIndex);
                System.out.println("Replaced!");
            } else if (ch == 8) {
                System.out.println("From ship:");
                for (int i = 0; i < ships.size(); i++) {
                    System.out.println(i + ": " + ships.get(i).name);
                }
                int a = Integer.parseInt(sc.nextLine());
                System.out.println("To ship:");
                int b = Integer.parseInt(sc.nextLine());
                System.out.println("Serial?");
                String srl = sc.nextLine();
                Container cc = null;
                for (Container c : ships.get(a).containers) {
                    if (c.getSerialNumber().equals(srl)) {
                        cc = c;
                        break;
                    }
                }
                if (cc != null && ships.get(b).addContainer(cc)) {
                    ships.get(a).removeContainer(srl);
                    System.out.println("Moved");
                } else {
                    System.out.println("Transfer failed");
                }
            } else if (ch == 9) {
                for (int i = 0; i < containers.size(); i++) {
                    System.out.println(i + ": " + containers.get(i).getSerialNumber());
                }
                int i = Integer.parseInt(sc.nextLine());
                Container c = containers.get(i);
                System.out.println("Cargo: " + c.getCargoMass() + "/" + c.getMaxCapacity());
            } else if (ch == 10) {
                for (int i = 0; i < ships.size(); i++) {
                    System.out.println(i + ": " + ships.get(i).name);
                }
                int i = Integer.parseInt(sc.nextLine());
                ships.get(i).printShipInfo();
            } else if (ch == 0) {
                System.out.println("Goodbye ");
                break;
            } else {
                System.out.println("Error");
            }
        }
    }
}
