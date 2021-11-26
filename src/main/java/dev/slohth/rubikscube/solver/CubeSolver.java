package dev.slohth.rubikscube.solver;

import dev.slohth.rubikscube.cube.Cube;
import dev.slohth.rubikscube.cube.CubeFace;
import dev.slohth.rubikscube.cube.CubeRotation;
import dev.slohth.rubikscube.cubit.Cubit;

import java.util.HashSet;
import java.util.Set;

public class CubeSolver {

    private final Cube cube;

    public CubeSolver(Cube cube) { this.cube = cube; }

    public void solveRedCross() {

        do {
            this.moveDownEdgesToTop();
            this.moveFaceEdgesToTop();
        } while (this.getRedEdgesOnFace(CubeFace.UP).size() < 4);

        this.allignTopCrossToBottom();
        System.out.println(this.getRedEdgesOnFace(CubeFace.DOWN).size() == 4);

    }

    private void moveDownEdgesToTop() {
        int[] data = new int[] { 1, 3, 5, 7 };
        int[] adj = new int[] { 7, 3, 5, 1 };
        CubeRotation[] rots = new CubeRotation[] { CubeRotation.FRONT, CubeRotation.LEFT, CubeRotation.RIGHT, CubeRotation.BACK };

        for (Cubit edge : getRedEdgesOnFace(CubeFace.DOWN)) {
            int pos = edge.getIndexInCube();
            for (int i = 0; i < data.length; i++) {
                if (CubeFace.DOWN.getCubits()[data[i]] == pos) {
                    while (cube.getCubits()[adj[i]].getOrientation()[0] == 5) {
                        cube.rotate(CubeRotation.UP);
                    }
                    cube.rotate(rots[i]); cube.rotate(rots[i]);
                }
            }
        }
    }

    private void moveFaceEdgesToTop() {

        for (CubeFace face : new CubeFace[] { CubeFace.FRONT, CubeFace.RIGHT, CubeFace.BACK, CubeFace.LEFT}) {
            switch (face) {
                case FRONT -> {

                    while (!this.getRedEdgesOnFace(face).isEmpty()) {
                        Cubit leftCheck = cube.getCubits()[face.getCubits()[3]];
                        if (leftCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[3].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.LEFT_PRIME);
                        }

                        Cubit rightCheck = cube.getCubits()[face.getCubits()[5]];
                        if (rightCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[5].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.RIGHT);
                        }

                        Cubit topCheck = cube.getCubits()[face.getCubits()[1]];
                        Cubit bottomCheck = cube.getCubits()[face.getCubits()[7]];
                        if (topCheck.getOrientation()[face.getId()] == 5) {
                            cube.rotate(CubeRotation.FRONT);
                        } else if (bottomCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[7].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.FRONT);
                        }
                    }

                }
                case RIGHT -> {

                    while (!this.getRedEdgesOnFace(face).isEmpty()) {
                        Cubit leftCheck = cube.getCubits()[face.getCubits()[3]];
                        if (leftCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[7].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.FRONT_PRIME);
                        }

                        Cubit rightCheck = cube.getCubits()[face.getCubits()[5]];
                        if (rightCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[1].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.BACK);
                        }

                        Cubit topCheck = cube.getCubits()[face.getCubits()[1]];
                        Cubit bottomCheck = cube.getCubits()[face.getCubits()[7]];
                        if (topCheck.getOrientation()[face.getId()] == 5) {
                            cube.rotate(CubeRotation.RIGHT);
                        } else if (bottomCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[5].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.RIGHT);
                        }
                    }

                }
                case BACK -> {

                    while (!this.getRedEdgesOnFace(face).isEmpty()) {
                        Cubit leftCheck = cube.getCubits()[face.getCubits()[3]];
                        if (leftCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[5].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.RIGHT_PRIME);
                        }

                        Cubit rightCheck = cube.getCubits()[face.getCubits()[5]];
                        if (rightCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[3].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.LEFT);
                        }

                        Cubit topCheck = cube.getCubits()[face.getCubits()[1]];
                        Cubit bottomCheck = cube.getCubits()[face.getCubits()[7]];
                        if (topCheck.getOrientation()[face.getId()] == 5) {
                            cube.rotate(CubeRotation.BACK);
                        } else if (bottomCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[1].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.BACK);
                        }
                    }

                }
                case LEFT -> {

                    while (!this.getRedEdgesOnFace(face).isEmpty()) {
                        Cubit leftCheck = cube.getCubits()[face.getCubits()[3]];
                        if (leftCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[1].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.BACK_PRIME);
                        }

                        Cubit rightCheck = cube.getCubits()[face.getCubits()[5]];
                        if (rightCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[7].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.FRONT);
                        }

                        Cubit topCheck = cube.getCubits()[face.getCubits()[1]];
                        Cubit bottomCheck = cube.getCubits()[face.getCubits()[7]];
                        if (topCheck.getOrientation()[face.getId()] == 5) {
                            cube.rotate(CubeRotation.LEFT);
                        } else if (bottomCheck.getOrientation()[face.getId()] == 5) {
                            while (cube.getCubits()[3].getOrientation()[0] == 5) {
                                cube.rotate(CubeRotation.UP);
                            }
                            cube.rotate(CubeRotation.LEFT);
                        }
                    }

                }
            }
        }

    }

    private void allignTopCrossToBottom() {

        int[] edges = new int[] { 3, 7, 5, 1 };
        for (int i = 0; i < edges.length; i++) {

            for (int check = 0; check < 4; check++) {
                if (cube.getCubits()[CubeFace.UP.getCubits()[edges[i]]].getOrientation()[0] != 5) {
                    cube.rotate(CubeRotation.UP);
                } else {
                    Cubit c = cube.getCubits()[CubeFace.UP.getCubits()[edges[i]]];
                    if (c.getOrientation()[i + 1] == (i + 1)) {
                        CubeRotation rot = CubeRotation.valueOf(CubeFace.getById(i + 1).toString());
                        cube.rotate(rot); cube.rotate(rot);
                    } else {
                        cube.rotate(CubeRotation.UP);
                    }
                }
            }

        }

    }

    private Set<Cubit> getRedEdgesOnFace(CubeFace face) {
        Set<Cubit> edges = new HashSet<>();
        byte[] cubits = face.getCubits();
        for (int index : new int[] { 1, 3, 5, 7 }) {
            Cubit c = cube.getCubits()[cubits[index]];
            if (c.getOrientation()[face.getId()] == 5) edges.add(c);
        }
        return edges;
    }

}
