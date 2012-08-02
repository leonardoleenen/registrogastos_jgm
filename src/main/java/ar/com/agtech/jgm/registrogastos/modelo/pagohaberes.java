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
import java.util.Set;
import java.util.HashSet;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Future;
import javax.persistence.CascadeType;

@Entity
public class pagohaberes implements java.io.Serializable
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
         return id.equals(((pagohaberes) that).id);
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
   private String tiponroprocedimiento;

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
   private String ordencompra;

   public String getOrdencompra()
   {
      return this.ordencompra;
   }

   public void setOrdencompra(final String ordencompra)
   {
      this.ordencompra = ordencompra;
   }

   @Past
   @NotNull
   private @Temporal(TemporalType.DATE)
   Date fechaInicio;

   public Date getFechaInicio()
   {
      return this.fechaInicio;
   }

   public void setFechaInicio(final Date fechaInicio)
   {
      this.fechaInicio = fechaInicio;
   }

   @Future
   @NotNull
   private @Temporal(TemporalType.DATE)
   Date fechaFinalizacion;

   public Date getFechaFinalizacion()
   {
      return this.fechaFinalizacion;
   }

   public void setFechaFinalizacion(final Date fechaFinalizacion)
   {
      this.fechaFinalizacion = fechaFinalizacion;
   }

   @Column
   private int cantperCobraHaberesPorBanco;

   public int getCantperCobraHaberesPorBanco()
   {
      return this.cantperCobraHaberesPorBanco;
   }

   public void setCantperCobraHaberesPorBanco(final int cantperCobraHaberesPorBanco)
   {
      this.cantperCobraHaberesPorBanco = cantperCobraHaberesPorBanco;
   }

   public String toString()
   {
      String result = "";
      if (tiponroprocedimiento != null && !tiponroprocedimiento.trim().isEmpty())
         result += tiponroprocedimiento;
      if (ordencompra != null && !ordencompra.trim().isEmpty())
         result += " " + ordencompra;
      result += " " + cantperCobraHaberesPorBanco;
      return result;
   }

   
}