import java.sql.*;
import java.util.ArrayList;

public class ResultSetReader {
    public static class List {
        public static String readStrings(String selectColumn, ResultSet resultSet) {
            try {
                return resultSet.getString(selectColumn);
                // it is a good idea to release resources in a finally{} block
                // in reverse-order of their creation if they are no-longer needed

            } catch (SQLException ex) {
                // This will result in an exception
                handleSQLException(ex);
                return null;
            }
        }

        public static Integer readInts(String selectColumn, ResultSet resultSet) {
            try {
                // ArrayList<Integer> tableContents = new ArrayList<Integer>();
                // resultSet.beforeFirst();

              

                   return resultSet.getInt(selectColumn);

                    // it is a good idea to release resources in a finally{} block
                    // in reverse-order of their creation if they are no-longer needed
                
                // resultSet.close();!
                // System.out.println("closed succesfully");

                // for (Integer integer : tableContents) {
                //     System.out.println("Integer: " + integer);
                // }

                // return tableContents;

            } catch (SQLException ex) {
                // This will result in an exception
                handleSQLException(ex);
                resultSet = null;
                return null;
            }
        }

        public static Float readFloats(String selectColumn, ResultSet resultSet) {
            try {
                //ArrayList<Float> tableContents = new ArrayList<Float>();
                // resultSet.beforeFirst();!

                // System.out.println("imhere");
               // while (resultSet.next()) {

                   return resultSet.getFloat(selectColumn);

                    // it is a good idea to release resources in a finally{} block
                    // in reverse-order of their creation if they are no-longer needed
                
                // resultSet.close();!
                // System.out.println("closed succesfully");

                // return tableContents;

            } catch (SQLException ex) {
                // This will result in an exception
                handleSQLException(ex);
                resultSet = null;
                return null;
            }
        }

        public static Boolean readBooleans(String selectColumn, ResultSet resultSet) {
            try {
                //ArrayList<Boolean> tableContents = new ArrayList<Boolean>();
                // resultSet.beforeFirst();!

                //while (resultSet.next()) {
                   return resultSet.getBoolean(selectColumn);

                    // it is a good idea to release resources in a finally{} block
                    // in reverse-order of their creation if they are no-longer needed
                
                // resultSet.close();!
              //  System.out.println("closed succesfully");

               // return tableContents;

            } catch (SQLException ex) {
                // This will result in an exception
                handleSQLException(ex);
                resultSet = null;
                return null;
            }
        }

    }

    public static class SingleValue {
        public static String readString(String selectColumn, ResultSet resultSet) {
            try {
                String tableContent = "";
                resultSet.beforeFirst();

                if (resultSet.next()) {
                    tableContent = resultSet.getString(selectColumn);
                    // it is a good idea to release resources in a finally{} block
                    // in reverse-order of their creation if they are no-longer needed
                }
                resultSet.close();
                System.out.println("closed successfully");

                return tableContent;

            } catch (SQLException ex) {
                // This will result in an exception
                handleSQLException(ex);
                resultSet = null;
                return null;
            }
        }

        public static Integer readInt(String selectColumn, ResultSet resultSet) {
            try {
                Integer tableContent = 0;
                resultSet.beforeFirst();

                // System.out.println("imhere");
                if (resultSet.next()) {
                    tableContent = resultSet.getInt(selectColumn);
                    // it is a good idea to release resources in a finally{} block
                    // in reverse-order of their creation if they are no-longer needed
                }
                resultSet.close();
                System.out.println("closed succesfully");

                return tableContent;

            } catch (SQLException ex) {
                // This will result in an exception
                handleSQLException(ex);
                resultSet = null;
                return null;
            }
        }

        public static Float readFloat(String selectColumn, ResultSet resultSet) {
            try {
                Float tableContents = 0f;
                resultSet.beforeFirst();

                // System.out.println("imhere");
                if (resultSet.next()) {
                    tableContents = resultSet.getFloat(selectColumn);
                    // it is a good idea to release resources in a finally{} block
                    // in reverse-order of their creation if they are no-longer needed
                }
                resultSet.close();
                System.out.println("closed succesfully");

                return tableContents;

            } catch (SQLException ex) {
                // This will result in an exception
                handleSQLException(ex);
                resultSet = null;
                return null;
            }
        }

        public static Boolean readBoolean(String selectColumn, ResultSet resultSet) {
            try {
                Boolean tableContents = false;
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    tableContents = resultSet.getBoolean(selectColumn);
                    // it is a good idea to release resources in a finally{} block
                    // in reverse-order of their creation if they are no-longer needed
                }
                resultSet.close();
                System.out.println("closed succesfully");

                return tableContents;

            } catch (SQLException ex) {
                // This will result in an exception
                handleSQLException(ex);
                resultSet = null;
                return null;
            }
        }

        public static Date readDate(String selectColumn, ResultSet resultSet) {
            try {
                Date tableContents = null;
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    tableContents = resultSet.getDate(selectColumn);
                    // it is a good idea to release resources in a finally{} block
                    // in reverse-order of their creation if they are no-longer needed
                }
                resultSet.close();
                System.out.println("closed succesfully");

                return tableContents;

            } catch (SQLException ex) {
                // This will result in an exception
                handleSQLException(ex);
                resultSet = null;
                return null;
            }
        }
        // TODO: read date
    }

    private static void handleSQLException(SQLException ex) {
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
        System.out.println("Connection with the database couldn't be established");
    }
}
