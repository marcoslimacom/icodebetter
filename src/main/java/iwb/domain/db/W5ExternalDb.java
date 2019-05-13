package iwb.domain.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// Generated Feb 5, 2007 3:58:07 PM by Hibernate Tools 3.2.0.b9

@Entity
@Immutable
@Table(name="w5_external_db",schema="iwb")
public class W5ExternalDb implements java.io.Serializable, W5Base{

	private int externalDbId;

	private short lkpDbType;// //oracle, postgre, mssql
	 
	private String dbUrl;

	private String dbUsername;

	private String dbPassword;

	private short activeFlag;// //oracle, postgre, mssql
	private int poolSize;// //oracle, postgre, mssql
	 

	private String projectUuid;
	
//	private HikariConfig _hikariConfig;
	private HikariDataSource _hikariDS;
	
	@Id
	@Column(name="project_uuid")
	public String getProjectUuid() {
		return projectUuid;
	}

	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}

	@Id
	@Column(name="external_db_id")
	public int getExternalDbId() {
		return externalDbId;
	}

	public void setExternalDbId(int externalDbId) {
		this.externalDbId = externalDbId;
	}

	@Column(name="lkp_db_type")
	public short getLkpDbType() {
		return lkpDbType;
	}

	public void setLkpDbType(short lkpDbType) {
		this.lkpDbType = lkpDbType;
	}

	@Column(name="db_url")
	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	@Column(name="db_username")
	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	@Column(name="db_password")
	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	@Column(name="active_flag")
	public short getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(short activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	
	@Column(name="pool_size")
	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	@Transient
	public boolean safeEquals(W5Base q){
		return false;
	}
	
	@Transient
	public Connection getConnection() throws SQLException {
		if(poolSize<2)
			return DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
		
		if(_hikariDS==null) {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(getDbUrl());
			config.setUsername(getDbUsername());
			config.setPassword(getDbPassword());
			config.setJdbcUrl(getDbUrl());
			config.setMaximumPoolSize(poolSize);
			config.setPoolName("icbPool-"+externalDbId);
			config.setReadOnly(true);
			_hikariDS = new HikariDataSource(config);
		}
        return _hikariDS.getConnection();
    }
}