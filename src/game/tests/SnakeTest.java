package game.tests;

import game.Coordinate;
import game.Direction;
import game.Field;
import game.Snake;
import objects.EmptySpace;
import objects.PieceOfSnake;

import static org.junit.Assert.*;

import org.junit.Test;

public class SnakeTest {

	@Test
	public void testPushFront() {
		Coordinate startCoordinate = new Coordinate(0, 0);
		Field field = new Field(new Coordinate(100, 1));
		Snake snake = new Snake(startCoordinate, field);
		snake.pushFront(field);
		snake.pushFront(field);
		assertEquals(snake.size(), 4);
		for (int i = 0; i < 4; i++) {
			assertTrue(field.getObjectOnField(new Coordinate(i, 0)) instanceof PieceOfSnake);
		}
	}

	@Test
	public void testIsPossibleToMove() {
		Field field = new Field(new Coordinate(4, 3));
		Snake snake = new Snake(new Coordinate(1, 1), field);
		field.surroundByWall();
		assertFalse(snake.isPossibleToMove(field));
	}

	@Test
	public void testPopFront_cantMakeSnakeShorterThan3() {
		Coordinate startCoordinate = new Coordinate(1, 0);
		Field field = new Field(new Coordinate(100, 1));
		Snake snake = new Snake(startCoordinate, field);
		snake.pushFront(field);
		snake.popFront(field);
		assertEquals(snake.size(), 3);
	}

	@Test
	public void testPopFront_makeSnakeShorter() {
		Field field = new Field(new Coordinate(100, 1));
		Snake snake = new Snake(new Coordinate(0, 0), field);
		snake.pushFront(field);
		snake.pushFront(field);
		snake.pushFront(field);
		snake.popFront(field);
		assertEquals(snake.size(), 4);
		assertTrue(field.getObjectOnField(new Coordinate(5, 0)) instanceof EmptySpace);
	}

	@Test
	public void testPopBack() {
		Field field = new Field(new Coordinate(100, 1));
		Snake snake = new Snake(new Coordinate(0, 0), field);
		snake.pushFront(field);
		snake.pushFront(field);
		snake.pushFront(field);
		snake.popBack(field);
		assertEquals(snake.size(), 4);
		assertTrue(field.getObjectOnField(new Coordinate(0, 0)) instanceof EmptySpace);
	}

	@Test
	public void testPushBack() {
		Field field = new Field(new Coordinate(100, 1));
		Snake snake = new Snake(new Coordinate(0, 0), field);
		snake.pushFront(field);
		snake.pushFront(field);
		snake.pushFront(field);
		snake.popBack(field);
		snake.pushBack(field);
		assertEquals(snake.size(), 5);
		assertTrue(field.getObjectOnField(new Coordinate(0, 0)) instanceof PieceOfSnake);
	}

}