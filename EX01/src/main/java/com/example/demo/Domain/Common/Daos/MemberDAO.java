package com.example.demo.Domain.Common.Daos;

import com.example.demo.Domain.Common.Dtos.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberDAO {

    @Autowired
    private DataSource dataSource3;   // DataSourceConfig 의 HikariDataSource 빈

    // TODO: 회원 1건 등록
    //  - dataSource3.getConnection() 으로 Connection 획득
    //  - "insert into tbl_member values(null,?,?,?,?)" PreparedStatement 생성
    //  - name / email / phone / createAt(Timestamp.valueOf(LocalDateTime.now())) 바인딩
    //  - executeUpdate() 결과(int) 반환
    public int insert(MemberDTO dto) throws SQLException {
        // insert 메서드를 사용하여 MTO 객체를 DB에 넣음
        try(
                Connection conn = dataSource3.getConnection();
                // dataSource3 객체로부터 Connection 을 하나 얻어옴
                PreparedStatement pstmt = conn.prepareStatement("insert into tbl_member values(null,?,?,?,?)")
                // PreparedStatement 객체를 생성
        ){
            pstmt.setString(1,dto.getName());
            pstmt.setString(2,dto.getEmail());
            pstmt.setString(3,dto.getPhone());
            pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            // 각 순서 별 ? 자리에 이름, 이메일, 전화번호를 셋팅

            int result =pstmt.executeUpdate();
            return result;
            // SQL 쿼리문을 실행한 후에 메서드 종료
        }
    }

    // TODO: 회원 전체 조회
    //  - "select * from tbl_member order by id desc" 실행
    //  - ResultSet 을 돌며 MemberDTO.builder() 로 매핑하여 List 로 반환
    public List<MemberDTO> selectAll() throws SQLException {

        try(
                Connection conn = dataSource3.getConnection();
                // dataSource3 에서 Connection을 가져옴
                PreparedStatement pstmt = conn.prepareStatement("select * from tbl_member order by id desc");
                ResultSet rs = pstmt.executeQuery();
                // 전체 회원을 조회하는 SQL문 실행하여 ResultSet 객체에 담음
        ){
            List<MemberDTO> list = new ArrayList<>();
            // 회원 객체들을 담아 리턴할 리스트 생성
            MemberDTO dto = null;
            // 각 행에 데이터를 담아둘 MemberDTO 참조 변수를 선언
            while(rs.next()){
                // 데이터가 있는 동안 while 문이 반복
                dto = MemberDTO.builder()
                        .name(rs.getString("Name"))
                        .email(rs.getString("Email"))
                        .phone(rs.getString("Phone"))
                        .createAt(rs.getTimestamp("createAt").toLocalDateTime())
                        .build();
                list.add(dto);
                // 셋팅된 값들을 바탕으로 최종적인 MemberDTO 객체 를 완성
            }
            return list;
        }
    }

}
