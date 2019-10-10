import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Randomize {
    private Long randomLong;
    private Boolean numberHasBeenPlayed;
    private Boolean answerWasCorrect;

    public Randomize() {
    }

    public Randomize(Long lBound, Long uBound) {
        this.randomLong = ThreadLocalRandom.current().nextLong(lBound,uBound);
        this.numberHasBeenPlayed = false;
    }

    public Long getRandomLong() {
        return randomLong;
    }

    public void setRandomLong(Long lBound, Long uBound) {

        this.randomLong = ThreadLocalRandom.current().nextLong(lBound,uBound);

    }

    public Boolean getNumberHasBeenPlayed() {
        return numberHasBeenPlayed;
    }

    public void setNumberHasBeenPlayed(Boolean numberHasBeenPlayed) {
        this.numberHasBeenPlayed = numberHasBeenPlayed;
    }

    public Boolean getAnswerWasCorrect() {
        return answerWasCorrect;
    }

    public void setAnswerWasCorrect(Boolean answerWasCorrect) {
        this.answerWasCorrect = answerWasCorrect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Randomize that = (Randomize) o;
        return randomLong.equals(that.randomLong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(randomLong);
    }
}
