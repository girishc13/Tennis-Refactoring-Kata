public class TennisGame1 implements TennisGame {

	private static final String SCORE_SEPARATOR = "-";
	private static final String WIN_FOR_PREFIX = "Win for ";
	private static final String ADVANTAGE_PREFIX = "Advantage ";
	private static final String DEUCE = "Deuce";
	private static final String THIRTY_ALL = "Thirty-All";
	private static final String FIFTEEN_ALL = "Fifteen-All";
	private static final String LOVE_ALL = "Love-All";
	private static final String FORTY = "Forty";
	private static final String THIRTY = "Thirty";
	private static final String FIFTEEN = "Fifteen";
	private static final String LOVE = "Love";
	private int playerOnePoint = 0;
	private int playerTwoPoint = 0;
	private String playerOneName;
	private String playerTwoName;

	public TennisGame1(String playerOneName, String playerTwoName) {
		this.playerOneName = playerOneName;
		this.playerTwoName = playerTwoName;
	}

	public void wonPoint(String playerName) {
		if (playerName == this.playerOneName)
			playerOnePoint += 1;
		else
			playerTwoPoint += 1;
	}

	public String getScore() {
		String score = "";
		if (isPlayerPointsTied()) {
			score = setScoreIfTied();
		} else if (isEitherPlayerPointGreaterThanFour()) {
			score = calcAdvantageOrWin();
		} else {
			score = calcScoreBasedOnIndividualPlayerPoints(score);
		}
		return score;
	}

	private String calcScoreBasedOnIndividualPlayerPoints(String score) {
		score = createScoreWithPlayerOnePoints(score);
		return appendPlayerTwoScore(score);
	}

	private String appendPlayerTwoScore(String score) {
		score += SCORE_SEPARATOR;
		score = appendPointsToStringScore(score, playerTwoPoint);
		return score;
	}

	private String createScoreWithPlayerOnePoints(String score) {
		score = appendPointsToStringScore(score, playerOnePoint);
		return score;
	}

	private String appendPointsToStringScore(String score, int tempScore) {
		switch (tempScore) {
		case 0:
			score += LOVE;
			break;
		case 1:
			score += FIFTEEN;
			break;
		case 2:
			score += THIRTY;
			break;
		case 3:
			score += FORTY;
			break;
		}
		return score;
	}

	private String calcAdvantageOrWin() {
		String score;
		int pointDiff = playerOnePoint - playerTwoPoint;

		if (pointDiff == 1)
			score = ADVANTAGE_PREFIX + this.playerOneName;
		else if (pointDiff == -1)
			score = ADVANTAGE_PREFIX + this.playerTwoName;
		else if (pointDiff >= 2)
			score = WIN_FOR_PREFIX + this.playerOneName;
		else
			score = WIN_FOR_PREFIX + this.playerTwoName;
		return score;
	}

	private boolean isEitherPlayerPointGreaterThanFour() {
		return playerOnePoint >= 4 || playerTwoPoint >= 4;
	}

	private boolean isPlayerPointsTied() {
		return playerOnePoint == playerTwoPoint;
	}

	private String setScoreIfTied() {
		String score;
		switch (playerOnePoint) {
		case 0:
			score = LOVE_ALL;
			break;
		case 1:
			score = FIFTEEN_ALL;
			break;
		case 2:
			score = THIRTY_ALL;
			break;
		default:
			score = DEUCE;
			break;

		}
		return score;
	}
}
