package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private ChessGame.TeamColor color;
    private PieceType pieceType;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        color = pieceColor;
        pieceType = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return color == that.color && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, pieceType);
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return pieceType;
    }
    public void blackPawnMoves(ChessBoard board, ChessPosition myPosition, int limit, int rowDir, int colDir, Collection<ChessMove> possibleMoves, boolean isDiagonal){
        for (int i = 1; i <= limit; i++) {
            ChessPosition nextPiece = new ChessPosition((myPosition.getRow() + i * rowDir), (myPosition.getColumn() + i * colDir));
            if (nextPiece.getRow() < 1 || nextPiece.getRow() > 8 || nextPiece.getColumn() < 1 || nextPiece.getColumn() > 8) {
                break;
            }
            if(board.getPiece(nextPiece) == null && !isDiagonal) {
                if (nextPiece.getRow() == 1) {
                    possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.QUEEN));
                    possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.ROOK));
                    possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.BISHOP));
                    possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.KNIGHT));
                }
                else{
                    possibleMoves.add(new ChessMove(myPosition,nextPiece,null));
                    if(myPosition.getRow() == 7){
                        ChessPosition nextPosition = new ChessPosition(myPosition.getRow() -2, myPosition.getColumn());
                        if(board.getPiece(nextPosition) == null){
                            possibleMoves.add(new ChessMove(myPosition,nextPosition,null));
                        }
                    }
                }
            }
            if(board.getPiece(nextPiece) != null){
                if(board.getPiece(nextPiece).getTeamColor() == ChessGame.TeamColor.WHITE){
                    if (nextPiece.getRow() == 1) {
                        possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.QUEEN));
                        possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.ROOK));
                        possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.BISHOP));
                        possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.KNIGHT));
                    }
                    else{
                        possibleMoves.add(new ChessMove(myPosition,nextPiece,null));
                    }
                    break;
                }
            }
        }
    }

    public void whitePawnMoves(ChessBoard board, ChessPosition myPosition, int limit, int rowDir, int colDir, Collection<ChessMove> possibleMoves, boolean isDiagonal){
        for (int i = 1; i <= limit; i++) {
            ChessPosition nextPiece = new ChessPosition((myPosition.getRow() + i * rowDir), (myPosition.getColumn() + i * colDir));
            if (nextPiece.getRow() < 1 || nextPiece.getRow() > 8 || nextPiece.getColumn() < 1 || nextPiece.getColumn() > 8) {
                break;
            }
            if(board.getPiece(nextPiece) == null && isDiagonal == false) {
                if (nextPiece.getRow() == 8) {
                    possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.QUEEN));
                    possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.ROOK));
                    possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.BISHOP));
                    possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.KNIGHT));
                }
                else{
                    possibleMoves.add(new ChessMove(myPosition,nextPiece,null));
                    if(myPosition.getRow() == 2){
                        ChessPosition nextPosition = new ChessPosition(myPosition.getRow() +2, myPosition.getColumn());
                        if(board.getPiece(nextPosition) == null){
                            possibleMoves.add(new ChessMove(myPosition,nextPosition,null));
                        }
                    }
                }
            }
            if(board.getPiece(nextPiece) != null){
                if(board.getPiece(nextPiece).getTeamColor() == ChessGame.TeamColor.BLACK ){
                    if(!isDiagonal){
                        break;
                    }
                    if (nextPiece.getRow() == 8) {
                        possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.QUEEN));
                        possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.ROOK));
                        possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.BISHOP));
                        possibleMoves.add(new ChessMove(myPosition, nextPiece, PieceType.KNIGHT));
                    }
                    else{
                        possibleMoves.add(new ChessMove(myPosition,nextPiece,null));
                    }
                    break;
                }
            }
        }
    }


    public void moveAcrossBoard(ChessBoard board, ChessPosition myPosition, int limit, int rowDir, int colDir, Collection<ChessMove> possibleMoves ){
        for (int i = 1; i <= limit; i++){
            ChessPosition nextPiece = new ChessPosition((myPosition.getRow() + i*rowDir), (myPosition.getColumn()+ i*colDir));
            if (nextPiece.getRow() < 1 || nextPiece.getRow() > 8 || nextPiece.getColumn() < 1 || nextPiece.getColumn() > 8){
                break;
            }
            if(board.getPiece(nextPiece) == null){
                possibleMoves.add(new ChessMove(myPosition,nextPiece,null));
            } else{
                if(board.getPiece(nextPiece).getTeamColor() != color){
                    possibleMoves.add(new ChessMove(myPosition,nextPiece,null));
                }
                break;
            }
        }
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new HashSet<>();
        if (pieceType == PieceType.ROOK){
            int limit = 8;
            int colLeft = -1;
            int colRight = 1;
            int colSame = 0;
            int rowUp = 1;
            int rowDown = -1;
            int rowSame = 0;

            /// Move Up
            moveAcrossBoard(board,myPosition,limit,rowUp,colSame,possibleMoves);
            /// Move Down
            moveAcrossBoard(board,myPosition,limit,rowDown,colSame,possibleMoves);
            /// Move Left
            moveAcrossBoard(board,myPosition,limit,rowSame,colLeft,possibleMoves);
            /// Move Right
            moveAcrossBoard(board,myPosition,limit,rowSame,colRight,possibleMoves);
        }
        if (pieceType == PieceType.BISHOP){
            int limit = 8;
            int colLeft = -1;
            int colRight = 1;
            int colSame = 0;
            int rowUp = 1;
            int rowDown = -1;
            int rowSame = 0;

            /// Move Diagonal Up Right
            moveAcrossBoard(board,myPosition,limit,rowUp,colRight,possibleMoves);
            /// Move Diagonal Up Left
            moveAcrossBoard(board,myPosition,limit,rowUp,colLeft,possibleMoves);
            /// Move Diagonal Down Right
            moveAcrossBoard(board,myPosition,limit,rowDown,colRight,possibleMoves);
            /// Move Diagonal Down Left
            moveAcrossBoard(board,myPosition,limit,rowDown,colLeft,possibleMoves);
        }
        if (pieceType == PieceType.KING) {
            int limit = 1;
            int colLeft = -1;
            int colRight = 1;
            int colSame = 0;
            int rowUp = 1;
            int rowDown = -1;
            int rowSame = 0;

            /// Move Up
            moveAcrossBoard(board,myPosition,limit,rowUp,colSame,possibleMoves);
            /// Move Down
            moveAcrossBoard(board,myPosition,limit,rowDown,colSame,possibleMoves);
            /// Move Left
            moveAcrossBoard(board,myPosition,limit,rowSame,colLeft,possibleMoves);
            /// Move Right
            moveAcrossBoard(board,myPosition,limit,rowSame,colRight,possibleMoves);
            /// Move Up Right
            moveAcrossBoard(board,myPosition,limit,rowUp,colRight,possibleMoves);
            /// Move Up Left
            moveAcrossBoard(board,myPosition,limit,rowUp,colLeft,possibleMoves);
            /// Move Down Right
            moveAcrossBoard(board,myPosition,limit,rowDown,colRight,possibleMoves);
            /// Move Down Left
            moveAcrossBoard(board,myPosition,limit,rowDown,colLeft,possibleMoves);
        }
        if (pieceType == PieceType.QUEEN) {
            int limit = 8;
            int colLeft = -1;
            int colRight = 1;
            int colSame = 0;
            int rowUp = 1;
            int rowDown = -1;
            int rowSame = 0;

            /// Move Up
            moveAcrossBoard(board, myPosition, limit, rowUp, colSame, possibleMoves);
            /// Move Down
            moveAcrossBoard(board, myPosition, limit, rowDown, colSame, possibleMoves);
            /// Move Left
            moveAcrossBoard(board, myPosition, limit, rowSame, colLeft, possibleMoves);
            /// Move Right
            moveAcrossBoard(board, myPosition, limit, rowSame, colRight, possibleMoves);
            /// Move Diagonal Up Right
            moveAcrossBoard(board, myPosition, limit, rowUp, colRight, possibleMoves);
            /// Move Diagonal Up Left
            moveAcrossBoard(board, myPosition, limit, rowUp, colLeft, possibleMoves);
            /// Move Diagonal Down Right
            moveAcrossBoard(board, myPosition, limit, rowDown, colRight, possibleMoves);
            /// Move Diagonal Down Left
            moveAcrossBoard(board, myPosition, limit, rowDown, colLeft, possibleMoves);
        }
        if (pieceType == PieceType.KNIGHT){
            int limit = 1;
            int rowUpOne = 1;
            int rowUpTwo = 2;
            int rowDownOne = -1;
            int rowDownTwo = -2;
            int colRightOne = 1;
            int colRightTwo = 2;
            int colLeftOne = -1;
            int colLeftTwo = -2;

            /// Up 1 Right 2
            moveAcrossBoard(board,myPosition,limit,rowUpOne,colRightTwo,possibleMoves);
            /// Up 1 Left 2
            moveAcrossBoard(board,myPosition,limit,rowUpOne,colLeftTwo,possibleMoves);
            /// Up 2 Right 1
            moveAcrossBoard(board,myPosition,limit,rowUpTwo,colRightOne,possibleMoves);
            /// Up 2 Left 1
            moveAcrossBoard(board,myPosition,limit,rowUpTwo,colLeftOne,possibleMoves);
            /// Down 1 Right 2
            moveAcrossBoard(board,myPosition,limit,rowDownOne,colRightTwo,possibleMoves);
            /// Down 1 Left 2
            moveAcrossBoard(board,myPosition,limit,rowDownOne,colLeftTwo,possibleMoves);
            /// Down 2 Right 1
            moveAcrossBoard(board,myPosition,limit,rowDownTwo,colRightOne,possibleMoves);
            /// Down 2 Left 1
            moveAcrossBoard(board,myPosition,limit,rowDownTwo,colLeftOne,possibleMoves);
        }
        if (pieceType == PieceType.PAWN){
            if(color == ChessGame.TeamColor.WHITE){
                int limit = 1;
                int rowUp = 1;
                int colSame = 0;
                int colRight = 1;
                int colLeft = -1;
                boolean diagonal = true;
                boolean notDiagonal = false;

                /// Up One
                whitePawnMoves(board,myPosition,limit,rowUp,colSame,possibleMoves,notDiagonal);

                /// Check Up 1 Right 1
                whitePawnMoves(board,myPosition,limit,rowUp,colRight,possibleMoves,diagonal);

                /// Check Up 1 Left 1
                whitePawnMoves(board,myPosition,limit,rowUp,colLeft,possibleMoves,diagonal);
            }
            if(color == ChessGame.TeamColor.BLACK){
                int limit = 1;
                int rowDown = -1;
                int colSame = 0;
                int colRight = 1;
                int colLeft = -1;
                boolean diagonal = true;
                boolean notDiagonal = false;

                /// Down One
                blackPawnMoves(board,myPosition,limit,rowDown,colSame,possibleMoves,notDiagonal);

                /// Check Down 1 Right 1
                blackPawnMoves(board,myPosition,limit,rowDown,colRight,possibleMoves,diagonal);

                /// Check Down 1 Left 1
                blackPawnMoves(board,myPosition,limit,rowDown,colLeft,possibleMoves,diagonal);
            }
        }


        return possibleMoves;
    }
}
