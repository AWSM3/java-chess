package com.lanit.chess.player;

import com.lanit.chess.Color;
import com.lanit.chess.board.BoardPoint;
import com.lanit.chess.util.Randomizer;
import com.lanit.chess.piece.Chessman;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract public class Player {
	protected Color color;
	protected BoardPoint[] points;
	protected boolean loser = false;

	public Player(Color color) {
		this.color = color;
	}

	public String toString() {
		return this.getColor().name();
	}

	public Color getColor() {
		return this.color;
	}

	public Player setPoints(BoardPoint[] points) {
		this.points = points;

		return this;
	}

	public boolean hasPieces() {
		return this.getPersonalPoints().length > 0;

	}

	public double getPiecesCount() {
		return this.getPersonalPoints().length;
	}

	private BoardPoint[] getPersonalPoints() {
		List<BoardPoint> personalPoints = new ArrayList<>();

		for (BoardPoint point : this.points) {
			if (point.getPiece() != null && point.getPiece().getColor() == this.getColor()) {
				personalPoints.add(point);
			}
		}

		return personalPoints.toArray(new BoardPoint[personalPoints.size()]);
	}

	public BoardPoint getRandomPoint() {
		BoardPoint[] playerPoints = this.getPersonalPoints();

		return this.getRandomPoint(playerPoints);		
	}

	public BoardPoint getRandomPoint(BoardPoint[] preset) {
		BoardPoint point = preset[Randomizer.generate(0, preset.length - 1)];
		if (point != null) {
 			return point;
 		}

 		return this.getRandomPoint(preset);
	}

	public boolean isLoser() {
		return this.loser || !this.hasPieces();
	}

	public void lose() {
		this.loser = true;
	}
}