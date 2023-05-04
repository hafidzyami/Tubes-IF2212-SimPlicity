public class Test {
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_RESET = "\u001B[0m";
    
        public static void main(String[] args) {
            System.out.println(ANSI_PURPLE + "  ________   __  _______  _____________   ___  ______" );
            System.out.println(" / ___/ _ | /  |/  / __/ / __/_  __/ _ | / _ \\/_  __/");
            System.out.println("/ (_ / __ |/ /|_/ / _/  _\\ \\  / / / __ |/ , _/ / /   ");
            System.out.println("\\___/_/ |_/_/  /_/___/ /___/ /_/ /_/ |_/_/|_| /_/    \n"+ ANSI_RESET);
            System.out.println("|  |__| |  /  _____  \\  |  |  |  | |  |____ ");
            //System.out.println(" \\______| /__/     \\__\\ |__|  |__| |_______|");
        }
}
