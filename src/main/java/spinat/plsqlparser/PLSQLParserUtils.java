package spinat.plsqlparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by fkarsany on 2015.03.12..
 */
public class PLSQLParserUtils {

    private static Seq scan(String s) {
        ArrayList<Token> a = Scanner.scanAll(s);
        ArrayList<Token> r = new ArrayList<Token>();
        for (Token t : a) {
            if (Scanner.isRelevant(t)) {
                r.add(t);
            }
        }
        return new Seq(r);
    }

    public static T2<Ast.PackageSpec, Ast.PackageBody> parsePackageSpecBody(String s) {
        Parser p = new Parser();
        Seq seq = scan(s);
        return p.paCRPackageSpecAndBody(seq);
    }

    public static T2<Ast.PackageSpec, Ast.PackageBody> parsePackageSpecBody(File f) {
        try {
            return parsePackageSpecBody(Util.loadFile(f.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
