import java.util.*;

class Episode {
    String title;
    int runtimeSec;

    Episode(String title, int runtimeSec) {
        this.title = title;
        this.runtimeSec = runtimeSec;
    }

    public String getTitle() {
        return title;
    }

    public int getRuntimeSec() {
        return runtimeSec;
    }
}