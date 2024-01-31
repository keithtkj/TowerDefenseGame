public class Player {
    private String name;
    private int money;
    private int addAmount;

    public Player(Difficulty difficulty, String name) {
        this.name = name;
        switch (difficulty) {
        case EASY:
            this.money = 1000;
            this.addAmount = 30;
            break;
        case MEDIUM:
            this.money = 600;
            this.addAmount = 20;
            break;
        case HARD:
            this.money = 400;
            this.addAmount = 10;
            break;
        default:
            this.money = 0;
            this.addAmount = 0;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }
}
