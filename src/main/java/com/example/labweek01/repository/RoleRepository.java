package com.example.labweek01.repository;

import com.example.labweek01.models.Account;
import com.example.labweek01.models.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class RoleRepository extends  GenericCRUD<Role> {
    public Optional<List<Role>> findRolesByIDAcc(String id){
        Transaction tr=null;
        try(Session session=sesssionFactory.openSession()){
            tr=session.beginTransaction();

            String sql = "SELECT role_id, role_name, description, status\n" +
                    "FROM role\n" +
                    "WHERE role_id IN (\n" +
                    "    SELECT role_id\n" +
                    "    FROM grant_access\n" +
                    "    WHERE account_id = :id\n" +
                    ");";
            List<Role> roles = session.createNativeQuery(sql, Role.class).setParameter("id",id).getResultList();


            tr.commit();
            return Optional.of(roles);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            tr.rollback();
            return  Optional.empty();
        }

    }

    public Role findRoleByID(String id){
        Transaction tr=null;
        try(Session session=sesssionFactory.openSession()){
            tr=session.beginTransaction();
            Role role=session.get(Role.class,id);
            tr.commit();
            return role;
        }catch (Exception e) {
}
        return null;
    }
}
