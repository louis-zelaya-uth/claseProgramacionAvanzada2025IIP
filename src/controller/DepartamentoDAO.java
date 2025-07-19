/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import bd.ConnectionDB;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import model.Departamento;
/**
 *
 * @author louis
 */
public class DepartamentoDAO {
    
    public List<Object> getAll(){
        List<Object> listado = new ArrayList<>();
        String query = "SELECT * FROM departamentos;";
        try (Connection con = ConnectionDB.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            while(resultado.next()){
                listado.add(new Departamento(resultado.getInt("id"),resultado.getString("nombre")));
            }        
        }catch(SQLException ex){
            System.err.println("Error al listar departamento: " + ex.getMessage());
        }        
        return listado;
    }
    
    public boolean insert(Object object){
        Departamento departamento = (Departamento) object;
        String sql = "INSERT INTO departamentos (nombre) VALUES (?);";
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, departamento.getNombre());
            return pst.executeUpdate()> 0;          
            
        }catch(SQLException ex){
            System.err.println("Error al insertar departamento: " + ex.getMessage());
            return false;
        }      
    }
    
    public boolean update(Object object){
        Departamento departamento = (Departamento) object;
        String sql = "UPDATE departamentos set nombre = ? Where id = ?;";
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, departamento.getNombre());
            pst.setInt(2, departamento.getId());
            return pst.executeUpdate()> 0;          
            
        }catch(SQLException ex){
            System.err.println("Error al actualizar departamento: " + ex.getMessage());
            return false;
        }      
    }
    
    public boolean delete(int id){        
        String sql = "DELETE FROM departamentos WHERE id = ?;";
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement pst = con.prepareStatement(sql);            
            pst.setInt(1, id);
            return pst.executeUpdate()> 0;         
            
        }catch(SQLException ex){
            System.err.println("Error al eliminar departamento: " + ex.getMessage());
            return false;
        }      
    }
    
    public Object getById(int id){        
        String query = "SELECT * FROM departamentos WHERE id = ?;";
        Departamento departamento = new Departamento();
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);            
            ResultSet resultado = pst.executeQuery();
            while(resultado.next()){
                departamento.setId(resultado.getInt("id"));
                departamento.setNombre(resultado.getString("nombre"));
            }        
        }catch(SQLException ex){
            System.err.println("Error al listar departamento: " + ex.getMessage());
        }        
        return departamento;
    }
}
