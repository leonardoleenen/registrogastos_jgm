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
public class Pasaje implements java.io.Serializable
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
         return id.equals(((Pasaje) that).id);
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

   @NotNull
   @Column
   private int cantPasajesContratadosUltimoAnio;

   public int getCantPasajesContratadosUltimoAnio()
   {
      return this.cantPasajesContratadosUltimoAnio;
   }

   public void setCantPasajesContratadosUltimoAnio(final int cantPasajesContratadosUltimoAnio)
   {
      this.cantPasajesContratadosUltimoAnio = cantPasajesContratadosUltimoAnio;
   }

   @Column
   private String destinoDePasajes;

   public String getDestinoDePasajes()
   {
      return this.destinoDePasajes;
   }

   public void setDestinoDePasajes(final String destinoDePasajes)
   {
      this.destinoDePasajes = destinoDePasajes;
   }

   @NotNull
   @Column
   private String empresaAerea;

   public String getEmpresaAerea()
   {
      return this.empresaAerea;
   }

   public void setEmpresaAerea(final String empresaAerea)
   {
      this.empresaAerea = empresaAerea;
   }

   @Column
   private String agenciaTurismo;

   public String getAgenciaTurismo()
   {
      return this.agenciaTurismo;
   }

   public void setAgenciaTurismo(final String agenciaTurismo)
   {
      this.agenciaTurismo = agenciaTurismo;
   }

   @NotNull
   @Column
   private int montoPasaje;

   public int getMontoPasaje()
   {
      return this.montoPasaje;
   }

   public void setMontoPasaje(final int montoPasaje)
   {
      this.montoPasaje = montoPasaje;
   }

   @NotNull
   @Column
   private String tipoNroProcedimiento;

   public String getTipoNroProcedimiento()
   {
      return this.tipoNroProcedimiento;
   }

   public void setTipoNroProcedimiento(final String tipoNroProcedimiento)
   {
      this.tipoNroProcedimiento = tipoNroProcedimiento;
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

   public String toString()
   {
      String result = "";
      result += cantPasajesContratadosUltimoAnio;
      if (destinoDePasajes != null && !destinoDePasajes.trim().isEmpty())
         result += " " + destinoDePasajes;
      if (empresaAerea != null && !empresaAerea.trim().isEmpty())
         result += " " + empresaAerea;
      if (agenciaTurismo != null && !agenciaTurismo.trim().isEmpty())
         result += " " + agenciaTurismo;
      result += " " + montoPasaje;
      if (tipoNroProcedimiento != null && !tipoNroProcedimiento.trim().isEmpty())
         result += " " + tipoNroProcedimiento;
      if (nroOrdenCompra != null && !nroOrdenCompra.trim().isEmpty())
         result += " " + nroOrdenCompra;
      return result;
   }
}