import java.awt.*;

public class SudokuSolver {
    public static final int SIZE = 9;
    public static final int BOXES = 3;
    public static final int BLANK = 0;
    public static final int ERROR = -1;

    public SudokuSolver(){}

    public void solve(int matrix[][]) {
	int vmat[][] = new int[SIZE][SIZE];
	int prevmat[][] = new int[SIZE][SIZE];

	int loop = 0;
	while(isSame(matrix, prevmat) == false) {
	    loop++;
	    copyMatrix(matrix, prevmat);
	    for(int i=1; i<=SIZE; i++){
		if(isCompleted(matrix) == false) {
		    complementMatrix(matrix, vmat, i);
		}
	    }
	}

    }

    private int countNumberInMatrix(int matrix[][], int sample, int xbegin, int xend, int ybegin, int yend)
    {
	int count = 0;
	for(int y=ybegin; y<yend; y++) {
	    for(int x=xbegin; x<xend; x++) {
		if(matrix[y][x] == sample) {
		    count++;
		}
	    }
	}
	return count;
    }

    private int findBlankNumberInMatrix(int matrix[][], int xbegin, int xend, int ybegin, int yend)
    {
	int candidate[] = new int[SIZE];
	for(int i=0; i<SIZE; i++) {
	    candidate[i] = i+1;
	}

	for(int y=ybegin; y<yend; y++) {
	    for(int x=xbegin; x<xend; x++) {
		if(matrix[y][x] != BLANK) {
		    candidate[matrix[y][x]-1] = BLANK;
		}
	    }
	}

	for(int i=0; i<SIZE; i++) {
	    if(candidate[i] != BLANK) {
		return candidate[i];
	    }
	}

	return BLANK;
    }

    private void copyMatrix(int src[][], int dest[][])
    {
	for(int y=0; y<SIZE; y++) {
	    for(int x=0; x<SIZE; x++) {
		dest[y][x] = src[y][x];
	    }
	}
    }

    private void fillMatrix(int matrix[][], int sample, int xbegin, int xend, int ybegin, int yend)
    {
	for(int y=ybegin; y<yend; y++) {
	    for(int x=xbegin; x<xend; x++) {
		matrix[y][x] = sample;
	    }
	}
    }

    private Point positionInMatrix(int matrix[][], int sample, int xbegin, int xend, int ybegin, int yend) {
	Point p = new Point();
	p.x = ERROR;
	p.y = ERROR;
	for(int y=ybegin; y<yend; y++) {
	    for(int x=xbegin; x<xend; x++) {
		if(matrix[y][x] == sample) {
		    p.x = x;
		    p.y = y;
		    return p;
		}
	    }
	}
	return p;
    }


    private void clearCandidate(int matrix[][], int vmat[][], int xbegin, int xend, int ybegin, int yend)
    {
	for(int y=ybegin; y<yend; y++) {
	    for(int x=xbegin; x<xend; x++) {
		if(matrix[y][x] == BLANK && vmat[y][x] == BLANK) {
		    matrix[y][x] = ERROR;
		}
	    }
	}
    }


    private boolean contains(int bigger[][], int smaller[][], int sample, int xbegin, int xend, int ybegin, int yend)
    {
	for(int y=ybegin; y<yend; y++) {
	    for(int x=xbegin; x<xend; x++) {
		if(smaller[y][x] == sample) {
		    if(bigger[y][x] == sample);
		    else {
			return false;
		    }
		}
	    }
	}
	return true;
    }


    private void refineCandidateWidh2Numbers(int matrix[][], int vmat[][], int sample) {
	int A[][] = new int[SIZE][SIZE];    
	int B[][] = new int[SIZE][SIZE];

	for(int na=1; na<=SIZE-1; na++) {
	    if(na == sample) {
		continue;
	    }
	    for(int nb=na+1; nb<=SIZE; nb++) {
		if(nb == sample) {
		    continue;
		}

		searchCandidate(matrix, A, na);
		searchCandidate(matrix, B, nb);

		for(int y=0; y<SIZE; y+=3) {
		    for(int x=0; x<SIZE; x+=3) {
			if( isSame(A, B, x, x+3, y, y+3) &&
			    contains(vmat, A, BLANK, x, x+3, y, y+3) &&
			    countNumberInMatrix(A, BLANK, x, x+3, y, y+3) == 2 ) {
			    clearCandidate(vmat, A, x, x+3, y, y+3);
			}
		    }
		}
	    }
	}

    }

    private void refineCandidate(int vmat[][])
    {
	int c = 0;
	for(int y=0; y<SIZE; y+=3) {
	    for(int x=0; x<SIZE; x+=3) {
		int count = countNumberInMatrix(vmat, BLANK, x, x+3, y, y+3);
		switch(count) {
		case 0:
		case 1:
		    break;
		case 2:
		case 3:
		    for(int v=y; v<y+3; v++) {
			c = 0;
			for(int h=x; h<x+3; h++) {
			    if(vmat[v][h] == BLANK) {
				c++;
			    }
			}
			if(c == count) {
			    for(int i=0; i<SIZE; i++) {
				if(i < x || x+3 <= i) {
				    vmat[v][i] = ERROR;
				}
			    }
			}
		    }
		    for(int h=x; h<x+3; h++) {
			c = 0;
			for(int v=y; v<y+3; v++) {
			    if(vmat[v][h] == BLANK) {
				c++;
			    }
			}
			if(c == count) {
			    for(int i=0; i<SIZE; i++) {
				if(i < y || y+3 <= i) {
				    vmat[i][h] = ERROR;
				}
			    }
			}
		    }
		    break;
		default:
		    break;
		}
	    }
	}
    }


    private void searchCandidate(int matrix[][], int vmat[][], int sample)
    {
	copyMatrix(matrix, vmat);
	for(int y=0; y<SIZE; y++) {
	    for(int x=0; x<SIZE; x++) {
		if(matrix[y][x] == sample) {
		    fillMatrix(vmat, ERROR, x, x+1, 0, SIZE);
		    fillMatrix(vmat, ERROR, 0, SIZE, y, y+1);
		    fillMatrix(vmat, ERROR, (x/3)*3, (x/3)*3+3, (y/3)*3, (y/3)*3+3);
		}
		if(matrix[y][x] >= 1 && matrix[y][x] <= SIZE) {
		    vmat[y][x] = ERROR;
		}
	    }
	}
	refineCandidate(vmat);
    }


    private void complementMatrix(int matrix[][], int vmat[][], int sample)
    {
	searchCandidate(matrix, vmat, sample);
	refineCandidateWidh2Numbers(matrix, vmat, sample);
	for(int y=0; y<SIZE; y++) {
	    for(int x=0; x<SIZE; x++) {
		if(y % 3 == 0 && x % 3 == 0) {
		    if(countNumberInMatrix(vmat, BLANK, x, x+3, y, y+3) == 1) {
			Point p = positionInMatrix(vmat, BLANK, x, x+3, y, y+3);
			if(p.x >= 0 && p.x < SIZE && p.y >= 0 && p.y < SIZE) {
			    matrix[p.y][p.x] = sample;
			}
		    }
		}
		else {
		    if(countNumberInMatrix(vmat, BLANK, 0, SIZE, y, y+1) == 1) {
			Point p = positionInMatrix(vmat, BLANK, 0, SIZE, y, y+1);
			if(p.x >= 0 && p.x < SIZE && p.y >= 0 && p.y < SIZE) {
			    matrix[p.y][p.x] = sample;
			}
		    }
		    if(countNumberInMatrix(vmat, BLANK, x, x+1, 0, SIZE) == 1) {
			Point p = positionInMatrix(vmat, BLANK, x, x+1, 0, SIZE);
			if(p.x >= 0 && p.x < SIZE && p.y >= 0 && p.y < SIZE) {
			    matrix[p.y][p.x] = sample;
			}
		    }
		}
	    }
	}
    }


    private boolean isCompleted(int matrix[][])
    {
	for(int y=0; y<SIZE; y++) {
	    for(int x=0; x<SIZE; x++) {
		if(matrix[y][x] == BLANK) {
		    return false;
		}
	    }
	}

	return true;
    }


    private boolean isSame(int lhs[][], int rhs[][], int xbegin, int xend, int ybegin, int yend)
    {
	for(int y=ybegin; y<yend; y++) {
	    for(int x=xbegin; x<xend; x++) {
		if(lhs[y][x] != rhs[y][x]) {
		    return false;
		}
	    }
	}
	return true;
    }

    private boolean isSame(int lhs[][], int rhs[][]) {
	return isSame(lhs, rhs, 0, SIZE, 0, SIZE);
    }

}

