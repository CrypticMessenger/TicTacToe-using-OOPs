import java.util.*; 
import java.util.Random;


class coordinate{
    private int row_num=0;
    private int col_num=0;
    public coordinate(int r,int c){
        this.row_num = r;
        this.col_num = c;
    }

    //*returns Row Number
    public int getRowNum() {
        return this.row_num;        
    }

    //*returns Column Number
    public int getColNum() {
        return this.col_num;        
    }
}



class person{
    public char character;
    public person(char c) {
        this.character =c; 
    }
}



class computer{
    public char character;
    public int prevX=0;
    public int prevY=0;
    public computer(char c){
        this.character = c;
    }


    //*checks if player can win at his turn , if yes, then returns best move to avoid it.
    public coordinate canCompleteRowColumnDiagonal(TicTacToe grid,int i,int j,person p) { 
        coordinate point = new coordinate(-1, -1);
        int numR = 0;
        int numC = 0;
        int numD1 = 0;
        int numD2 = 0;
        for(int x=0;x<3;x++){
            if(grid.getCharOnBoard(i,x)==p.character){
                numR++;
            }
            if(grid.getCharOnBoard(x,j)==p.character){
                numC++;
            }
            if(grid.getCharOnBoard(x,x)==p.character){
                numD1++;
            }
            if(grid.getCharOnBoard(x,2-x)==p.character){
                numD2++;
            }
        }
        
        if(numR==2){
            for(int x=0;x<3;x++){
                if(grid.getCharOnBoard(i,x)=='-'){
                    point = new coordinate(i, x);
                    break;
                }     
            }
        }
        else if(numC==2){
            for(int x=0;x<3;x++){
                if(grid.getCharOnBoard(x,j)=='-'){
                    point = new coordinate(x, j);
                    break;
                }          
            }
        }
        else if(numD1==2){
            for (int k = 0; k < 3; k++) {
                if(grid.getCharOnBoard(k,k)=='-'){
                    point= new coordinate(k, k);
                    break;
                }
            }

        }
        else if(numD2==2){
            for (int k = 0; k < 3; k++) {
                if(grid.getCharOnBoard(k,2-k)=='-'){
                    point= new coordinate(k, 2-k);
                    break;
                }
            }
        }
        return point; 
    }

    //*using polymorphism to check if computer can win in next move. if yes , it WILL win.
    public coordinate canCompleteRowColumnDiagonal(TicTacToe grid,int i,int j,computer p) { 
        coordinate point = new coordinate(-1, -1);
        int numR = 0;
        int numC = 0;
        int numD1 = 0;
        int numD2 = 0;
        for(int x=0;x<3;x++){
            if(grid.getCharOnBoard(i,x)==p.character){
                numR++;
            }
            if(grid.getCharOnBoard(x,j)==p.character){
                numC++;
            }
            if(grid.getCharOnBoard(x,x)==p.character){
                numD1++;
            }
            if(grid.getCharOnBoard(x,2-x)==p.character){
                numD2++;
            }
        }
        
        if(numR==2){
            for(int x=0;x<3;x++){
                if(grid.getCharOnBoard(i,x)=='-'){
                    point = new coordinate(i, x);
                    break;
                }     
            }
        }
        else if(numC==2){
            for(int x=0;x<3;x++){
                if(grid.getCharOnBoard(x,j)=='-'){
                    point = new coordinate(x, j);
                    break;
                }          
            }
        }
        else if(numD1==2){
            for (int k = 0; k < 3; k++) {
                if(grid.getCharOnBoard(k,k)=='-'){
                    point= new coordinate(k, k);
                    break;
                }
            }

        }
        else if(numD2==2){
            for (int k = 0; k < 3; k++) {
                if(grid.getCharOnBoard(k,2-k)=='-'){
                    point= new coordinate(k, 2-k);
                    break;
                }
            }
        }
        return point; 
    }


    //*makeMoveFunction but for computer, it checks for cases.
    public boolean makeMoveComp(TicTacToe grid,int i,int j,person p,computer c){
        int x0 = this.canCompleteRowColumnDiagonal(grid,this.prevX,this.prevY,c).getRowNum();//coordinate that can be filled to make computer win.
        int y0=this.canCompleteRowColumnDiagonal(grid,this.prevX,this.prevY,c).getColNum();//coordinate that can be filled to make computer win.
        int x = this.canCompleteRowColumnDiagonal(grid,i,j,p).getRowNum();//coordinate that can be filled to NOT make player win.
        int y=this.canCompleteRowColumnDiagonal(grid,i,j,p).getColNum();//coordinate that can be filled to NOT make player win.
        if(x0==-1 && y0==-1){
            if(x==-1 && y==-1){
                Random rand = new Random();
                x=rand.nextInt(3); 
                y=rand.nextInt(3);
                while(grid.isCellFilled(x, y)){
                    x=rand.nextInt(3); 
                    y=rand.nextInt(3);
                }
                grid.makeMove(x,y,this);
                this.prevX=x;
                this.prevY=y;
                if(grid.isRowColumnDiagonalComplete(x, y, this) ){
                    return true;
                }
                return false;
            }
            else{
                
                grid.makeMove(x,y,this);
                this.prevX=x;
                this.prevY=y;
                if(grid.isRowColumnDiagonalComplete(x,y,this)){
                    return true;
                }
                return false;
            }
        }
        else{
            grid.makeMove(x0,y0,this);
                this.prevX=x0;
                this.prevY=y0;
                if(grid.isRowColumnDiagonalComplete(x0,y0,this)){
                    return true;
                }
                return false;
        }

    }
}



class TicTacToe {
    private char[][] board = new char[3][3]; //*ticTacToe board
    private int turn = 0; //*stores number of turns happened till now.

    //*gives instructions to user/player.
    private void instruct() {
        System.out.println();
        System.out.println("for a particular cell here is the syntax | i j |");
        System.out.println("Following are the codes to access particualr cell to make your turn:");
        System.out.println();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print("| "+i+" "+j+" |");
            }
            System.out.println("");
        }
        System.out.println();
        System.out.println();
        this.render();
        
    }

    //*initialize function, makes board empty.
    private void init(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.board[i][j]='-';
            }
        }    
    }

    //*returns char on board at coordinates (i,j)
    public char getCharOnBoard(int i,int j){
        return this.board[i][j];
    }

    //*displays TicTacToe board and number of turns done.
    private void render() {
        System.out.println("Turns: "+ this.turn);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("| "+this.board[i][j]+" |");
            }
            System.out.println("");
        }
        System.out.println();
    }

    //*checks if cell is filled or not. returns boolean.
    public boolean isCellFilled(int i,int j) {
        if(this.board[i][j]!='-'){
            return true;
        }
        return false;
    }

    //*makes moves for the person (real player).
    private void makeMove(int i,int j,person p){   
        this.turn++;
        this.board[i][j]=p.character;
        this.render();
    }

    //*makes moves for computer.
    public void makeMove(int i,int j,computer p){   
        this.turn++;
        this.board[i][j]=p.character;
        this.render();
    }

    //* checks if there are 3 player characters in row/column/diagonal or not.
    private boolean isRowColumnDiagonalComplete(int i,int j,person p) {
        if(this.board[i][0]==this.board[i][1] && this.board[i][1]==this.board[i][2] && this.board[i][0]== p.character){
            return true;
        }
        else if(this.board[0][j]==this.board[1][j] && this.board[1][j]==this.board[2][j] && this.board[0][j]== p.character){
            return true;
        }
        else if(this.board[0][0]==this.board[1][1] && this.board[1][1]==this.board[2][2] && this.board[0][0]== p.character){
            return true;
        }
        else if(this.board[0][2]==this.board[1][1] && this.board[1][1]==this.board[2][0] && this.board[0][2]== p.character){
            return true;
        }
        return false;  
    }

    //*checks if there are 3 computer characters in row/column/diagonal or not.
    public boolean isRowColumnDiagonalComplete(int i,int j,computer p) {
        if(this.board[i][0]==this.board[i][1] && this.board[i][1]==this.board[i][2] && this.board[i][0]== p.character){
            return true;
        }
        else if(this.board[0][j]==this.board[1][j] && this.board[1][j]==this.board[2][j] && this.board[0][j]== p.character){
            return true;
        }
        else if(this.board[0][0]==this.board[1][1] && this.board[1][1]==this.board[2][2] && this.board[0][0]== p.character){
            return true;
        }
        else if(this.board[0][2]==this.board[1][1] && this.board[1][1]==this.board[2][0] && this.board[0][2]== p.character){
            return true;
        }
        return false;  
    }
    
    //*Play function for player Vs Player mode.
    private void play(TicTacToe grid,person pl1, person pl2) {
        Scanner sc = new Scanner(System.in);
        person [] index_pl = {pl1,pl2};     
        grid.init();
        int turn_est=0;//*turn estimater
        int i,j;
        int temp=0;//*stores number of turns till now
        int won =0;
        while(temp<9){
            grid.instruct();
            System.out.println("Player " + (turn_est+1) + " turn: " + " ("+ index_pl[turn_est].character +")");
            System.out.println("Enter row and column (0 indexing applicable):");
            i=sc.nextInt();
            j=sc.nextInt();
            while(grid.isCellFilled(i, j)){
                System.out.println("Cell already filled!");
                System.out.println("Enter row and column (0 indexing applicable):");
                i=sc.nextInt();
                j=sc.nextInt();
            }
            
            grid.makeMove(i, j, index_pl[turn_est]);
            if(grid.isRowColumnDiagonalComplete(i, j, index_pl[turn_est])){
                System.out.println("Player "+(turn_est+1) +" ("+index_pl[turn_est].character+")"+" won!");
                won=1;
                break;
            }
            turn_est^=1;
            temp++;
        }
        if(won==0){
            System.out.println("Game Tied!!");
        }
        sc.close();    
    }

    //*Play function for Player Vs Computer node.
    private void play(TicTacToe grid,person p1,computer c) {
        Scanner sc = new Scanner(System.in);
        grid.init();
        int turn_est=0; //*turn estimater
        int temp=0; //*number of turns till now

        int i =0 ;
        int j=0;
        
        int won=0;
        while(temp<9){
            if(turn_est==0){
                grid.instruct();
                System.out.println("Your Turn: ");
                System.out.println("Enter row and column (0 indexing applicable):");
                i=sc.nextInt();
                j=sc.nextInt();
                while(grid.isCellFilled(i, j)){
                    System.out.println("Cell already filled!");
                    System.out.println("Enter row and column (0 indexing applicable):");
                    i=sc.nextInt();
                    j=sc.nextInt();
                }
                
                grid.makeMove(i, j, p1);
                if(grid.isRowColumnDiagonalComplete(i, j, p1)){
                    System.out.println("You won!");
                    won=1;
                    break;
                }
            }
            else{
                if(c.makeMoveComp(grid,i,j,p1,c)){
                    System.out.println("You Lose!! HAHA!!");
                    won=1;
                    break;
                }
            }
            turn_est^=1;
            temp++;
        }
        if(won==0){
            System.out.println("Tie!!");
        }
        sc.close();
    }
    

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        TicTacToe grid = new TicTacToe();                               //*Creating a TicTacToe board
    
        System.out.println("Enter The option:");
        System.out.println("1 : P vs P");
        System.out.println("2 : P vs C");
        int options=sc.nextInt();
        
        if(options == 1){
            char p1,p2;
            System.out.println("enter player 1 character: ");
            p1 = sc.next().charAt(0);
            System.out.println("enter player 2 character: ");
            p2 = sc.next().charAt(0);
            person pl1 = new person(p1);                    //*creating player 1
            person pl2 = new person(p2);                    //*creating player 2
            grid.play(grid,pl1, pl2);                           //*Start playing
        }
        else if(options == 2){
            person pl1 = new person('X');                    //* creating player
            computer c = new computer('O');                  //* creating computer player
            grid.play(grid,pl1, c);                             //*start playing
        }
        sc.close();
    }
        
    


    
}
