import java.util.*;

public class StreamingServiceBingeMode {
    public static void main(String[] args) {
        Episode e1 = new Episode("Pilot", 30);
        Episode e2 = new Episode("Episode 2", 32);
        Episode e3 = new Episode("Season Finale", 34);

        Season season1 = new Season();
        season1.addEpisode(e1);
        season1.addEpisode(e2);

        Season season2 = new Season();
        season2.addEpisode(e3);

        Series series = new Series();
        series.addSeason(season1);
        series.addSeason(season2);
        Scanner scanner = new Scanner(System.in);
        int option;

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Watch episodes normally");
            System.out.println("2. Watch episodes in reverse");
            System.out.println("3. Watch episodes shuffled");
            System.out.println("4. Skip intro (enter seconds to skip)");
            System.out.println("5. Filter watched episodes");
            System.out.println("6. Exit");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Normal order iteration:");
                    for (Episode e : season1) {
                        System.out.println(e.getTitle());
                    }
                    break;

                case 2:
                    System.out.println("Reverse order iteration:");
                    Iterator<Episode> reverseIterator = season1.reverseIterator();
                    while (reverseIterator.hasNext()) {
                        System.out.println(reverseIterator.next().getTitle());
                    }
                    break;

                case 3:
                    System.out.println("Shuffle order iteration:");
                    Iterator<Episode> shuffleIterator = season1.shuffleIterator();
                    while (shuffleIterator.hasNext()) {
                        System.out.println(shuffleIterator.next().getTitle());
                    }
                    break;

                case 4:
                    System.out.print("Enter seconds to skip intro: ");
                    int skipSeconds = scanner.nextInt();
                    System.out.println("Using Skip Intro iterator (skip first " + skipSeconds + " seconds):");
                    Iterator<Episode> skipIntroIterator = season1.skipIntroIterator(skipSeconds);
                    while (skipIntroIterator.hasNext()) {
                        System.out.println(skipIntroIterator.next().getTitle());
                    }
                    break;

                case 5:
                    Set<Episode> watchedEpisodes = new HashSet<>();
                    watchedEpisodes.add(e1);
                    System.out.println("Using Watch History Filter iterator (only unseen episodes):");
                    Iterator<Episode> historyFilterIterator = season1.watchHistoryIterator(watchedEpisodes);
                    while (historyFilterIterator.hasNext()) {
                        System.out.println(historyFilterIterator.next().getTitle());
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
