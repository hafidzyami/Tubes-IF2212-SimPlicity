public class Main {
    public static void space() {
        System.out.println();
    }
    public static void main(String[] args) {
        World world = World.getZaWarudo();

        world.printMap();
        Sim Bobi = new Sim("Bobi Orange Milk");
        space();
        world.addNewHome();
        world.printMap();
    }
}
