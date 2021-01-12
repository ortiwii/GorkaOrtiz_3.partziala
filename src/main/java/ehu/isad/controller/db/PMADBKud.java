package ehu.isad.controller.db;

import ehu.isad.Model;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PMADBKud {

    private static PMADBKud nInst = new PMADBKud();
    private DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
    private PMADBKud (){}

    public static PMADBKud getInstance(){
        return nInst;
    }
    public Model bilatu (String url, String md5){
            try {
                String query = "SELECT version FROM checksums WHERE md5 = '"+md5+"';";
                ResultSet rs = dbkud.execSQL(query);
                String version = null;
                while (rs.next()){
                    version = rs.getString("version");
                }
                if (version != null){
                    return (new Model(url, md5, version));
                }else{
                    return (new Model(url, md5, ""));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
    }
    public void gehitu (Model model) {
        if (this.bilatu(model.getUrl(), model.getMd5()).getVersion().equals("")) {
            String query = "INSERT INTO checksums (idCMS, version, md5, path) VALUES (1, '" + model.getVersion() + "','" + model.getMd5() + "','README');";
            dbkud.execSQL(query);
        } else {
            String query = "update checksums set version = '" + model.getVersion() + "' where md5 = '" + model.getMd5() + "';";
            dbkud.execSQL(query);
        }
    }
}
