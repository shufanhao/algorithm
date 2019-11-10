package booking;

public class Solution {

    public static void main(String[] args) throws IOException {
        Player[] players = new Player[2];
        players[0] = new Player("davis", 15);
        players[1] = new Player("ela", 15);
        Arrays.sort(players, new Checker());
        System.out.println(Arrays.toString(players));
    }

    static class Checker implements Comparator<Player> {

        @Override
        public int compare(Player o1, Player o2) {
            if (o1.score != o2.score) {
                return o2.score - o1.score;
            }
            return o1.name.compareTo(o2.name);
        }
    }

    static class Player {

        private String name;
        private int score;

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
