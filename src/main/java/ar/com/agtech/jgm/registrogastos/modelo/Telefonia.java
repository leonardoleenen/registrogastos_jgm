package ar.com.agtech.jgm.registrogastos.modelo;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import javax.validation.constraints.NotNull;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Telefonia implements java.io.Serializable
{

   @Id
   private @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   Long id = null;
   @Version
   private @Column(name = "version")
   int version = 0;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((Telefonia) that).id);
      }
      return super.equals(that);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   @Column
   private @NotNull(message = "Indique el tipo y nro de Procedimiento")
   String tiponroprocedimiento;

   public String getTiponroprocedimiento()
   {
      return this.tiponroprocedimiento;
   }

   public void setTiponroprocedimiento(final String tiponroprocedimiento)
   {
      this.tiponroprocedimiento = tiponroprocedimiento;
   }

   @NotNull
   @Column
   private String empresaPrestadora;

   public String getEmpresaPrestadora()
   {
      return this.empresaPrestadora;
   }

   public void setEmpresaPrestadora(final String empresaPrestadora)
   {
      this.empresaPrestadora = empresaPrestadora;
   }

   @NotNull
   @Column
   private int montoServicio;

   public int getMontoServicio()
   {
      return this.montoServicio;
   }

   public void setMontoServicio(final int montoServicio)
   {
      this.montoServicio = montoServicio;
   }

   @Column
   private String ubicacionGeograficaDependencia;

   public String getUbicacionGeograficaDependencia()
   {
      return this.ubicacionGeograficaDependencia;
   }

   public void setUbicacionGeograficaDependencia(final String ubicacionGeograficaDependencia)
   {
      this.ubicacionGeograficaDependencia = ubicacionGeograficaDependencia;
   }

   @NotNull
   @Column
   private String nroOrdenCompra;

   public String getNroOrdenCompra()
   {
      return this.nroOrdenCompra;
   }

   public void setNroOrdenCompra(final String nroOrdenCompra)
   {
      this.nroOrdenCompra = nroOrdenCompra;
   }

   @NotNull
   private @Temporal(TemporalType.DATE)
   Date fechaInicioContrato;

   public Date getFechaInicioContrato()
   {
      return this.fechaInicioContrato;
   }

   public void setFechaInicioContrato(final Date fechaInicioContrato)
   {
      this.fechaInicioContrato = fechaInicioContrato;
   }

   @NotNull
   private @Temporal(TemporalType.DATE)
   Date fechaFinalizacionContrato;

   public Date getFechaFinalizacionContrato()
   {
      return this.fechaFinalizacionContrato;
   }

   public void setFechaFinalizacionContrato(final Date fechaFinalizacionContrato)
   {
      this.fechaFinalizacionContrato = fechaFinalizacionContrato;
   }

   @NotNull
   @Column
   private int cantMoviles;

   public int getCantMoviles()
   {
      return this.cantMoviles;
   }

   public void setCantMoviles(final int cantMoviles)
   {
      this.cantMoviles = cantMoviles;
   }

   @NotNull
   @Column
   private String tipoServicioContrato;

   public String getTipoServicioContrato()
   {
      return this.tipoServicioContrato;
   }

   public void setTipoServicioContrato(final String tipoServicioContrato)
   {
      this.tipoServicioContrato = tipoServicioContrato;
   }

   @Column
   private String nroLinea;

   public String getNroLinea()
   {
      return this.nroLinea;
   }

   public void setNroLinea(final String nroLinea)
   {
      this.nroLinea = nroLinea;
   }

   @NotNull
   @Column
   private String titular;

   public String getTitular()
   {
      return this.titular;
   }

   public void setTitular(final String titular)
   {
      this.titular = titular;
   }

   @NotNull
   @Column
   private String dependencia;

   public String getDependencia()
   {
      return this.dependencia;
   }

   public void setDependencia(final String dependencia)
   {
      this.dependencia = dependencia;
   }

   @Column
   private int montoUltimaFacturacion;

   public int getMontoUltimaFacturacion()
   {
      return this.montoUltimaFacturacion;
   }

   public void setMontoUltimaFacturacion(final int montoUltimaFacturacion)
   {
      this.montoUltimaFacturacion = montoUltimaFacturacion;
   }

   public String toString()
   {
      String result = "";
      if (tiponroprocedimiento != null && !tiponroprocedimiento.trim().isEmpty())
         result += tiponroprocedimiento;
      if (empresaPrestadora != null && !empresaPrestadora.trim().isEmpty())
         result += " " + empresaPrestadora;
      result += " " + montoServicio;
      if (ubicacionGeograficaDependencia != null && !ubicacionGeograficaDependencia.trim().isEmpty())
         result += " " + ubicacionGeograficaDependencia;
      if (nroOrdenCompra != null && !nroOrdenCompra.trim().isEmpty())
         result += " " + nroOrdenCompra;
      result += " " + cantMoviles;
      if (tipoServicioContrato != null && !tipoServicioContrato.trim().isEmpty())
         result += " " + tipoServicioContrato;
      if (nroLinea != null && !nroLinea.trim().isEmpty())
         result += " " + nroLinea;
      if (titular != null && !titular.trim().isEmpty())
         result += " " + titular;
      if (dependencia != null && !dependencia.trim().isEmpty())
         result += " " + dependencia;
      result += " " + montoUltimaFacturacion;
      return result;
   }
}