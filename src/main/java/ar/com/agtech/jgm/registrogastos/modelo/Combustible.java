package ar.com.agtech.jgm.registrogastos.modelo;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Combustible implements java.io.Serializable
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
         return id.equals(((Combustible) that).id);
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
   private @NotNull(message = "El tipo de Procedimiento Es obligatorio")
   String tipoNroProcedimiento;

   public String getTipoNroProcedimiento()
   {
      return this.tipoNroProcedimiento;
   }

   public void setTipoNroProcedimiento(final String tipoNroProcedimiento)
   {
      this.tipoNroProcedimiento = tipoNroProcedimiento;
   }

   @Column
   private @NotNull(message = "Indique la razon social del proveedor")
   String razonSocialProveedor;

   public String getRazonSocialProveedor()
   {
      return this.razonSocialProveedor;
   }

   public void setRazonSocialProveedor(final String razonSocialProveedor)
   {
      this.razonSocialProveedor = razonSocialProveedor;
   }

   @Column
   private @NotNull(message = "Indique el CUIT del proveedor")
   String cuitProveedor;

   public String getCuitProveedor()
   {
      return this.cuitProveedor;
   }

   public void setCuitProveedor(final String cuitProveedor)
   {
      this.cuitProveedor = cuitProveedor;
   }

   @Column
   private @NotNull(message = "Indique el nro de Orden De Compra")
   String nroOrdenCompra;

   public String getNroOrdenCompra()
   {
      return this.nroOrdenCompra;
   }

   public void setNroOrdenCompra(final String nroOrdenCompra)
   {
      this.nroOrdenCompra = nroOrdenCompra;
   }

   private @Temporal(TemporalType.DATE)
   @NotNull(message = "Indique la fecha de inicio del contrato")
   Date fechaInicioContrato;

   public Date getFechaInicioContrato()
   {
      return this.fechaInicioContrato;
   }

   public void setFechaInicioContrato(final Date fechaInicioContrato)
   {
      this.fechaInicioContrato = fechaInicioContrato;
   }

   private @Temporal(TemporalType.DATE)
   @NotNull(message = "Indique la fecha de finalizacion de contrato")
   Date fechaFinalizacionContrato;

   public Date getFechaFinalizacionContrato()
   {
      return this.fechaFinalizacionContrato;
   }

   public void setFechaFinalizacionContrato(final Date fechaFinalizacionContrato)
   {
      this.fechaFinalizacionContrato = fechaFinalizacionContrato;
   }

   @Column
   private @NotNull(message = "Indique la cantidad de litros utilizados en el mes")
   int cantLitrosPorMes;

   public int getCantLitrosPorMes()
   {
      return this.cantLitrosPorMes;
   }

   public void setCantLitrosPorMes(final int cantLitrosPorMes)
   {
      this.cantLitrosPorMes = cantLitrosPorMes;
   }

   @Column
   private String especificacionTecnicasProductosUtilizados;

   public String getEspecificacionTecnicasProductosUtilizados()
   {
      return this.especificacionTecnicasProductosUtilizados;
   }

   public void setEspecificacionTecnicasProductosUtilizados(final String especificacionTecnicasProductosUtilizados)
   {
      this.especificacionTecnicasProductosUtilizados = especificacionTecnicasProductosUtilizados;
   }

   @Column
   private @NotNull(message = "Indique el lugar de entrega del combustible")
   String lugarCargaCombustible;

   public String getLugarCargaCombustible()
   {
      return this.lugarCargaCombustible;
   }

   public void setLugarCargaCombustible(final String lugarCargaCombustible)
   {
      this.lugarCargaCombustible = lugarCargaCombustible;
   }

   public String toString()
   {
      String result = "";
      if (tipoNroProcedimiento != null && !tipoNroProcedimiento.trim().isEmpty())
         result += tipoNroProcedimiento;
      if (razonSocialProveedor != null && !razonSocialProveedor.trim().isEmpty())
         result += " " + razonSocialProveedor;
      if (cuitProveedor != null && !cuitProveedor.trim().isEmpty())
         result += " " + cuitProveedor;
      if (nroOrdenCompra != null && !nroOrdenCompra.trim().isEmpty())
         result += " " + nroOrdenCompra;
      result += " " + cantLitrosPorMes;
      if (especificacionTecnicasProductosUtilizados != null && !especificacionTecnicasProductosUtilizados.trim().isEmpty())
         result += " " + especificacionTecnicasProductosUtilizados;
      if (lugarCargaCombustible != null && !lugarCargaCombustible.trim().isEmpty())
         result += " " + lugarCargaCombustible;
      return result;
   }
}