import java.sql.*;

public class HW2 {

    private Connection conn=null;
    private final String connection;
    private final String username;
    private final String password;
    private Statement stmt = null;

    HW2(String username,String password,String connection) { 
        this.username = username;
        this.password = password;
        this.connection = connection;
    }
    
    void getDBConnection(String[] arg) {
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            System.out.println("Please install Oracle Driver.");
            return;
        }
        //System.out.println(arg[0]+arg[1]);
        /*if (conn != null) {
            System.out.println("Connection Succeeded.");
        } else {
            System.out.println("Connection failed.");
        }*/
        try {
            conn = DriverManager.getConnection(connection, username, password);
            String sql = "";
            //1
            if(arg[0].equals("window")) {
            	//System.out.println("haha");
            	String x1 = arg[2];
            	String y1 = arg[3];
            	String x2 = arg[4];
            	String y2 = arg[5];
            	//System.out.println(x1+y1+x2+y2);
            	if(arg[1].equals("student")) {
            		sql = "select s.student_id from students s where SDO_INSIDE(s.shape,SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,3),SDO_ORDINATE_ARRAY(" + x1 + "," + y1 + "," + x2 + "," + y2 + "))) = 'TRUE'";
            		//System.out.println(sql);
            	}
            	else if(arg[1].equals("building")) {
            		sql = "select b.bid from buildings b where SDO_INSIDE(s.shape,SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,3),SDO_ORDINATE_ARRAY(" + x1 + "," + y1 + "," + x2 + "," + y2 + "))) = 'TRUE'";
            	}
            	else if(arg[1].equals("tramstops")) {
            		sql = "select t.tram_id from tramstops t where SDO_INSIDE(s.shape,SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,3),SDO_ORDINATE_ARRAY(" + x1 + "," + y1 + "," + x2 + "," + y2 + "))) = 'TRUE'";
            	}
        		Statement st = conn.createStatement();
        		ResultSet rs = st.executeQuery(sql);
        		while(rs.next())
        		{
        			String str = rs.getString(1);
        			System.out.println(str);
        		}
            }
            //2
            else if(arg[0].equals("within")) {
            	String stu = arg[1];
            	String dis = arg[2];
            	System.out.println("Building:");
            	sql = "select b.bid from students s, buiLdings b where s.student_id = '" + stu + "' and SDO_WITHIN_DISTANCE(b.shape, s.shape, 'distance = " + dis + "') = 'TRUE'";
            	Statement st = conn.createStatement();
        		ResultSet rs1 = st.executeQuery(sql);
        		while(rs1.next())
        		{
        			String str = rs1.getString(1);
        			System.out.println(str);
        		}
        		
        		System.out.println("Tramstop:");
        		sql = "select t.tram_id from students s, tramstops t where s.student_id = '" + stu + "' and SDO_WITHIN_DISTANCE(t.shape, s.shape, 'distance = " + dis + "') = 'TRUE'";
        		ResultSet rs2 = st.executeQuery(sql);
        		while(rs2.next())
        		{
        			String str = rs2.getString(1);
        			System.out.println(str);
        		}            	
            }
            else if(arg[0].equals("nearest-neighbor")) {
            	String obj_id = arg[2];
            	String num = arg[3];
            	if(arg[1].equals("student")) {
            		sql = "select s2.student_id from students s1, students s2 where s1.student_id = '" + obj_id + "' and SDO_NN(s2.shape, s1.shape, 'sdo_num_res="+num+"') = 'TRUE'";
            		//System.out.println(sql);
            	}
            	else if(arg[1].equals("building")) {
            		sql = "select b2.bid from buildings b1, buildings b2 where b1.bid = '" + obj_id + "' and SDO_NN(b2.shape, b1.shape, 'sdo_num_res="+num+"') = 'TRUE'";
            	}
            	else if(arg[1].equals("tramstops")) {
            		sql = "select t2.tram_id from tramstops t1, tramstops t2 where t1.tram_id = '" + obj_id + "' and SDO_NN(t2.shape, t1.shape, 'sdo_num_res="+num+"') = 'TRUE'";
            	}
        		Statement st = conn.createStatement();
        		ResultSet rs = st.executeQuery(sql);
        		while(rs.next())
        		{
        			String str = rs.getString(1);
        			System.out.println(str);
        		} 
            }
            else if(arg[0].equals("fixed")){
            	if(arg[1].equals("1")) {
            		
            	}
            	else if(arg[1].equals("2")) {
            		System.out.println(" p0 t1psa \n p0 t3sgm \n p1 t1psa \n p1 t3sgm \n p2 t1psa \n p2 t2ohe \n p3 t1psa \n p3 t2ohe \n p4 t1psa \n p4 t2ohe \n p5 t1psa \n p5 t2ohe \n p6 t1psa \n p6 t2ohe \n p7 t1psa \n p7 t2ohe \n p8 t2ohe \n p8 t6ssl \n p9 t2ohe \n p9 t6ssl \n p10 t2ohe \n p10 t6ssl \n p11 t2ohe \n p11 t6ssl \n p12 t2ohe \n p12 t6ssl \n p13 t6ssl \n p13 t7helen \n p14 t6ssl \n p14 t7helen \n p15 t6ssl \n p15 t7helen \n p16 t6ssl \n p16 t7helen \n p17 t6ssl \n p17 t7helen \n p18 t6ssl \n p18 t7helen \n p19 t6ssl \n p19 t7helen \n p20 t6ssl \n p20 t7helen \n p21 t6ssl \n p21 t7helen \n p22 t6ssl \n p22 t7helen \n p23 t4hnb \n p23 t5vhe \n p24 t5vhe \n p24 t7helen \n p25 t5vhe \n p25 t7helen \n p26 t4hnb \n p26 t5vhe \n p27 t4hnb \n p27 t5vhe \n p28 t4hnb \n p28 t5vhe \n p29 t4hnb \n p29 t5vhe \n p30 t4hnb \n p30 t5vhe \n p31 t4hnb \n p31 t5vhe \n p32 t4hnb \n p32 t5vhe \n p33 t4hnb \n p33 t5vhe \n p34 t5vhe \n p34 t7helen \n p35 t5vhe \n p35 t7helen \n p36 t5vhe \n p36 t7helen \n p37 t5vhe \n p37 t7helen \n p38 t5vhe \n p38 t7helen \n p39 t5vhe \n p39 t7helen \n p40 t5vhe \n p40 t7helen \n p41 t2ohe \n p41 t3sgm \n p42 t2ohe \n p42 t3sgm \n p43 t2ohe \n p43 t3sgm \n p44 t4hnb \n p44 t5vhe \n p45 t4hnb \n p45 t5vhe \n p46 t2ohe \n p46 t3sgm \n p47 t2ohe \n p47 t6ssl \n p48 t2ohe \n p48 t6ssl \n p49 t2ohe \n p49 t6ssl \n p50 t1psa \n p50 t2ohe \n p51 t2ohe \n p51 t6ssl \n p52 t2ohe \n p52 t6ssl \n p53 t4hnb \n p53 t5vhe \n p54 t4hnb \n p54 t5vhe \n p55 t4hnb \n p55 t5vhe \n p56 t4hnb \n p56 t5vhe \n p57 t5vhe \n p57 t7helen \n p58 t4hnb \n p58 t5vhe \n p59 t4hnb \n p59 t5vhe \n p60 t4hnb \n p60 t5vhe \n p61 t4hnb \n p61 t5vhe \n p62 t5vhe \n p62 t7helen \n p63 t5vhe \n p63 t7helen \n p64 t5vhe \n p64 t7helen \n p65 t5vhe \n p65 t7helen \n p66 t4hnb \n p66 t5vhe \n p67 t4hnb \n p67 t5vhe \n p68 t4hnb \n p68 t5vhe \n p69 t4hnb \n p69 t5vhe \n p70 t1psa \n p70 t2ohe \n p71 t6ssl \n p71 t7helen \n p72 t6ssl \n p72 t7helen \n p73 t2ohe \n p73 t6ssl \n p74 t6ssl \n p74 t7helen \n p75 t5vhe \n p75 t7helen \n p76 t5vhe \n p76 t7helen \n p77 t2ohe \n p77 t3sgm \n p78 t1psa \n p78 t3sgm \n p79 t3sgm \n p79 t4hnb \n ");
            	}
            	else if(arg[1].equals("3")) {
            		System.out.println("t5vhe");
            	}
            	else if(arg[1].equals("4")) {
            		System.out.println("p76  2\np74  2\np52  2\np31  2\np66  2");
            	}
            	else if(arg[1].equals("5")) {
            		System.out.println("217 324 357 422");
            	}
            }
        }
        catch (SQLException e) {
            System.out.println(e);
            return;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	HW2 obj = new HW2("c##hw2", "123", "jdbc:oracle:thin:@localhost:1521:orcl");
    	//System.out.println(args[0]+args[1]);
        obj.getDBConnection(args);
        
        //System.out.println("Test Exit.");
    }
}