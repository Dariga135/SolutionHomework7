import java.util.Iterator;
import java.util.NoSuchElementException;

public class SkipIntroIterator implements Iterator<Episode> {
    private Iterator<Episode> episodeIterator;
    private int skipSeconds;

    public SkipIntroIterator(Iterator<Episode> episodeIterator, int skipSeconds) {
        this.episodeIterator = episodeIterator;
        this.skipSeconds = skipSeconds;
    }

    public boolean hasNext() {
        return episodeIterator.hasNext();
    }

    public Episode next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Episode episode = episodeIterator.next();
        System.out.println("Skipping intro for: " + episode.getTitle() + ". Starting at " + skipSeconds + " seconds.");
        return episode;
    }
}
