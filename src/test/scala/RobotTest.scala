package org.hanlonjohn23

import org.scalatest.funsuite.AnyFunSuite

class RobotTest extends AnyFunSuite {
  test("Robot should start at the origin point") {
    assert(new Robot().move("") == (0, 0))
  }

  test("'U' input should cause the robot to go up") {
    assert(new Robot().move("U") == (0, 1))
  }

  test("'D' input should cause the robot to go down") {
    assert(new Robot().move("D") == (0, -1))
  }

  test("'L' input should cause the robot to go left") {
    assert(new Robot().move("L") == (-1, 0))
  }

  test("'R' input should cause the robot to go right") {
    assert(new Robot().move("R") == (1, 0))
  }

  test("Invalid input should cause the robot to stay put") {
    assert(new Robot().move("Q") == (0, 0))
  }

  test("Robot should loop to the left when it travels more than ten units right") {
    assert(new Robot().move("RRRRRRRRRRR") == (-10, 0))
  }

  test("Robot should loop to the right when it travels more than ten units left") {
    assert(new Robot().move("LLLLLLLLLLL") == (10, 0))
  }

  test("Robot should loop to the bottom when it travels more than ten units up") {
    assert(new Robot().move("UUUUUUUUUUU") == (0, -10))
  }

  test("Robot should loop to the top when it travels more than ten units down") {
    assert(new Robot().move("DDDDDDDDDDD") == (0, 10))
  }
}

