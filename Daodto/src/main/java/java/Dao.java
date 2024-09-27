package java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao {

	Connection con = null;
	Statement st = null;

	public void del(String no) {

		try {

			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);
			st = con.createStatement();

			String sql = String.format("delete from %s where b_no=%s", Db.TABLE_PS_BOARD_FREE, no);
			st.executeUpdate(sql);

			st.close();
			con.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void write(Dto d) {

		try {

			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);
			st = con.createStatement();

			String sql = String.format(

					"insert into %s(b_title, b_id, b_text) values(%s,%s,%s)", Db.TABLE_PS_BOARD_FREE, d.title, d.id,
					d.text);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public Dto read(String no) {

		Dto post = null;
		try {

			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);
			st = con.createStatement();

			String sql = String.format(

					"select * from %S where b_no = %s", Db.TABLE_PS_BOARD_FREE, no);

			System.out.println("sql:" + sql);
			ResultSet rs = st.executeQuery(sql);
			rs.next();

			post = new Dto(

					rs.getString("B_NO"), rs.getString("B_TITLE"), rs.getString("B_ID"), rs.getString("B_DATETIME"),
					rs.getString("B_HIT"), rs.getString("B_TEXT"), rs.getString("B_REPLY_COUNT"),
					rs.getString("B_REPLY_ORI")

			);

			st.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return post;
	}

	public ArrayList<Dto> list() {

		ArrayList<Dto> posts = new ArrayList<>();

		try {

			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);
			st = con.createStatement();

			String sql = String.format(

					"select * from %s", Db.TABLE_PS_BOARD_FREE);

			System.out.println("sql:" + sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				posts.add(new Dto(

						rs.getString("B_NO"), rs.getString("B_TITLE"), rs.getString("B_ID"), rs.getString("B_DATETIME"),
						rs.getString("B_HIT"), rs.getString("B_TEXT"), rs.getString("B_REPLY_COUNT"),
						rs.getString("B_REPLY_ORI")

				));

			}

			st.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return posts;

	}

	public void edit(Dto d, String no) {

		try {

			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);
			st = con.createStatement();

			String sql = String.format(

					"update %s set b_title = '%s', b_text='%s' where b_no=%s", Db.TABLE_PS_BOARD_FREE, d.title, d.text,
					no);

			st.executeUpdate(sql);
			st.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
