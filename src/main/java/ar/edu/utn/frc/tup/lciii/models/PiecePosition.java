package ar.edu.utn.frc.tup.lciii.models;

import lombok.Getter;

import java.util.Objects;

@Getter
public class PiecePosition {

    private Integer row;

    private Integer column;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public PiecePosition() {
    }

    public PiecePosition(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PiecePosition other = (PiecePosition) obj;
        return this.row == other.row && this.column == other.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }








}
