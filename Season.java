import java.util.*;

public class Season implements Iterable<Episode> {
    private List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode e) {
        episodes.add(e);
    }

    public Iterator<Episode> iterator() {
        return new SeasonIterator();
    }

    public Iterator<Episode> skipIntroIterator(int skipSeconds) {
        return new SkipIntroIterator(new SeasonIterator(), skipSeconds);
    }

    public Iterator<Episode> watchHistoryIterator(Set<Episode> watchedEpisodes) {
        return new WatchHistoryFilterIterator(new SeasonIterator(), watchedEpisodes);
    }

    private class SeasonIterator implements Iterator<Episode> {
        private int index = 0;

        public boolean hasNext() {
            return index < episodes.size();
        }

        public Episode next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return episodes.get(index++);
        }
    }

    private class ReverseSeasonIterator implements Iterator<Episode> {
        private int index = episodes.size() - 1;

        public boolean hasNext() {
            return index >= 0;
        }

        public Episode next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return episodes.get(index--);
        }
    }

    private class ShuffleSeasonIterator implements Iterator<Episode> {
        private List<Episode> shuffledEpisodes;
        private int index = 0;

        ShuffleSeasonIterator() {
            shuffledEpisodes = new ArrayList<>(episodes);
            Collections.shuffle(shuffledEpisodes, new Random(42));
        }

        public boolean hasNext() {
            return index < shuffledEpisodes.size();
        }

        public Episode next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return shuffledEpisodes.get(index++);
        }
    }

    public Iterator<Episode> reverseIterator() {
        return new ReverseSeasonIterator();
    }

    public Iterator<Episode> shuffleIterator() {
        return new ShuffleSeasonIterator();
    }
}
