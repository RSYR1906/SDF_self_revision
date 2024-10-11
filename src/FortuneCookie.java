import java.util.Random;

public class FortuneCookie {

    static String[] fortunes = { "Adventure awaits those who dare to dream.",
            "A smile is your passport into the hearts of others.",
            "Opportunities are like sunrises, if you wait too long, you miss them.",
            "Your kindness will lead you to unexpected places." };

    public static void main(String[] args) {

        Random rand = new Random();
        int index = rand.nextInt(fortunes.length);
        System.out.println(fortunes[index]);
    }
}
