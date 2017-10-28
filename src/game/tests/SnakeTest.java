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
		Coordinate startCoordinate = new Coordinate(1, 0);
		Field field = new Field(new Coordinate(100, 1));
		Snake snake = new Snake(startCoordinate, field);
		snake.pushFront(field);
		snake.pushFront(field);
		assertEquals(snake.size(), 4);
		for (int i = 0; i < 4; i++) {
			assertTrue(field.getObjectOnField(new Coordinate(1 + i, 0)) instanceof PieceOfSnake);
		}
	}

	@Test
	public void testIsPossibleToMove() {
		Field field = new Field(new Coordinate(4, 3));
		Snake snake = new Snake(new Coordinate(2, 1), field);
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
		Coordinate startCoordinate = new Coordinate(1, 0);
		Field field = new Field(new Coordinate(100, 1));
		Snake snake = new Snake(startCoordinate, field);
		snake.pushFront(field);
		snake.pushFront(field);
		snake.pushFront(field);
		
		snake.popFront(field);
		
		assertEquals(snake.size(), 4);
		assertTrue(field.getObjectOnField(new Coordinate(0, 4)) instanceof EmptySpace);
		
	}

	@Test
	public void testPopBack() {
		Coordinate startCoordinate = new Coordinate(1, 0);
		Field field = new Field(new Coordinate(100, 1));
		Snake snake = new Snake(startCoordinate, field);
		assertEquals(snake.size(), 2);
		snake.pushFront(field);
		assertEquals(snake.size(), 3);
		snake.popBack(field);
		assertEquals(snake.size(), 3);
		snake.pushFront(field);
		assertEquals(snake.size(), 4);
		snake.popBack(field);
		assertEquals(snake.size(), 3);
		for (int i = 0; i < 100; i++) {
			snake.pushBack(field);
			assertTrue(field.getObjectOnField(new Coordinate(0, 0)) instanceof PieceOfSnake);
			assertEquals(snake.size(), 4);
			snake.popBack(field);
			assertTrue(field.getObjectOnField(new Coordinate(0, 0)) instanceof EmptySpace);
			assertEquals(snake.size(), 3);
		}

	}

	@Test
	public void testPushBack() {
		Coordinate startCoordinate = new Coordinate(1, 0);
		Field field = new Field(new Coordinate(100, 1));
		Snake snake = new Snake(startCoordinate, field);
		assertEquals(snake.size(), 2);
		snake.pushFront(field);
		assertEquals(snake.size(), 3);
		snake.popBack(field);
		assertEquals(snake.size(), 3);
		snake.pushFront(field);
		assertEquals(snake.size(), 4);
		snake.popBack(field);
		assertEquals(snake.size(), 3);
		snake.pushBack(field);
		assertEquals(snake.size(), 4);
	}

}