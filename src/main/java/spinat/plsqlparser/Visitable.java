package spinat.plsqlparser;

/**
 * Created by fkarsany on 2015.03.12..
 */
public interface Visitable {

    public void accept(Visitor visitor);

}
