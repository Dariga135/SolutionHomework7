import java.util.*;

public class Series {
    private List<Season> seasons = new ArrayList<>();

    public void addSeason(Season s) {
        seasons.add(s);
    }

    public Iterator<Episode> bingeIterator() {
        return new BingeIterator();
    }

    private class BingeIterator implements Iterator<Episode> {
        private Iterator<Season> seasonIterator = seasons.iterator();
        private Iterator<Episode> episodeIterator = null;

        public boolean hasNext() {
            if (episodeIterator == null || !episodeIterator.hasNext()) {
                if (seasonIterator.hasNext()) {
                    episodeIterator = seasonIterator.next().iterator();
                    return hasNext();
                }
                return false;
            }
            return episodeIterator.hasNext();
        }

        public Episode next() {
            return episodeIterator.next();
        }
    }
}
