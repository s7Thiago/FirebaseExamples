package br.com.thiagosousa.firebaseexamples.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Residuo implements Parcelable {
    private String nome;
    private boolean reciclavel;

    /*1: baterias - 2: eletronicos - 3:metais
     * 4: organicos - 5: outros - 6: papeis
     * 7: plasticos - 8: vidros*/
    private int categoria;
    private int representacao;


    public Residuo(String nome, int representacao, int categoria, boolean reciclavel) {
        this.nome = nome;
        this.reciclavel = reciclavel;
        this.categoria = categoria;
        this.representacao = representacao;
    }

    protected Residuo(Parcel in) {
        nome = in.readString();
        categoria = in.readInt();
        reciclavel = in.readByte() != 0;
    }

    public static final Creator<Residuo> CREATOR = new Creator<Residuo>() {
        @Override
        public Residuo createFromParcel(Parcel in) {
            return new Residuo(in);
        }

        @Override
        public Residuo[] newArray(int size) {
            return new Residuo[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public boolean isReciclavel() {
        return reciclavel;
    }

    public void setReciclavel(boolean reciclavel) {
        this.reciclavel = reciclavel;
    }

    public int getRepresentacao() {
        return representacao;
    }

    public void setRepresentacao(int representacao) {
        this.representacao = representacao;
    }

    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append("nome: " + this.getNome())
                .append("categoria: " + this.getCategoria())
                .append("É reciclável? : " + (this.isReciclavel() ? "Sim" : "Não")));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeInt(categoria);
        dest.writeByte((byte) (reciclavel ? 1 : 0));
    }
}
