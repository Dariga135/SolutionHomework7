import java.util.*;

public class WatchHistoryFilterIterator implements Iterator<Episode> {
    private Iterator<Episode> episodeIterator;
    private Set<Episode> watchedEpisodes;

    public WatchHistoryFilterIterator(Iterator<Episode> episodeIterator, Set<Episode> watchedEpisodes) {
        this.episodeIterator = episodeIterator;
        this.watchedEpisodes = watchedEpisodes;
    }

    public boolean hasNext() {
        while (episodeIterator.hasNext()) {
            Episode episode = episodeIterator.next();
            if (!watchedEpisodes.contains(episode)) {
                episodeIterator = new ArrayList<>(Collections.singletonList(episode)).iterator();
                return true;
            }
        }
        return false;
    }

    public Episode next() {
        return episodeIterator.next();
    }
}
