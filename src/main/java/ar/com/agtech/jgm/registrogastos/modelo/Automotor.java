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
import javax.validation.constraints.Future;

@Entity
public class Automotor implements java.io.Serializable
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
         return id.equals(((Automotor) that).id);
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
   private String tipo;

   public String getTipo()
   {
      return this.tipo;
   }

   public void setTipo(final String tipo)
   {
      this.tipo = tipo;
   }

   @NotNull
   @Column
   private String marca;

   public String getMarca()
   {
      return this.marca;
   }

   public void setMarca(final String marca)
   {
      this.marca = marca;
   }

   @Column
   private String modelo;

   public String getModelo()
   {
      return this.modelo;
   }

   public void setModelo(final String modelo)
   {
      this.modelo = modelo;
   }

   @NotNull
   @Column
   private int anio;

   public int getAnio()
   {
      return this.anio;
   }

   public void setAnio(final int anio)
   {
      this.anio = anio;
   }

   @NotNull
   @Column
   private String patente;

   public String getPatente()
   {
      return this.patente;
   }

   public void setPatente(final String patente)
   {
      this.patente = patente;
   }

   @NotNull
   @Column
   private String agenteUsuario;

   public String getAgenteUsuario()
   {
      return this.agenteUsuario;
   }

   public void setAgenteUsuario(final String agenteUsuario)
   {
      this.agenteUsuario = agenteUsuario;
   }

   @NotNull
   @Column
   private String dependenciaDestinado;

   public String getDependenciaDestinado()
   {
      return this.dependenciaDestinado;
   }

   public void setDependenciaDestinado(final String dependenciaDestinado)
   {
      this.dependenciaDestinado = dependenciaDestinado;
   }

   @NotNull
   @Column
   private String usoDestinado;

   public String getUsoDestinado()
   {
      return this.usoDestinado;
   }

   public void setUsoDestinado(final String usoDestinado)
   {
      this.usoDestinado = usoDestinado;
   }

   @Column
   private @NotNull(message = "El tipo y Nro de Procedimiento es obligatorio...")
   String tiponroprocedimiento;

   public String getTiponroprocedimiento()
   {
      return this.tiponroprocedimiento;
   }

   public void setTiponroprocedimiento(final String tiponroprocedimiento)
   {
      this.tiponroprocedimiento = tiponroprocedimiento;
   }

   @Column
   private @NotNull(message = "El encuadre legal es obligatorio")
   String formaAdquisicion;

   public String getFormaAdquisicion()
   {
      return this.formaAdquisicion;
   }

   public void setFormaAdquisicion(final String formaAdquisicion)
   {
      this.formaAdquisicion = formaAdquisicion;
   }

   @Column
   private @NotNull(message = "El numero de orden de compra es requerido")
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
   @Future(message = "La fecha debe estar en el futuro")
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
   private String firmaArrendataria;

   public String getFirmaArrendataria()
   {
      return this.firmaArrendataria;
   }

   public void setFirmaArrendataria(final String firmaArrendataria)
   {
      this.firmaArrendataria = firmaArrendataria;
   }

   @Column
   private Double montoMensual;

   public Double getMontoMensual()
   {
      return this.montoMensual;
   }

   public void setMontoMensual(final Double montoMensual)
   {
      this.montoMensual = montoMensual;
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

   public String toString()
   {
      String result = "";
      if (tipo != null && !tipo.trim().isEmpty())
         result += tipo;
      if (marca != null && !marca.trim().isEmpty())
         result += " " + marca;
      if (modelo != null && !modelo.trim().isEmpty())
         result += " " + modelo;
      result += " " + anio;
      if (patente != null && !patente.trim().isEmpty())
         result += " " + patente;
      if (agenteUsuario != null && !agenteUsuario.trim().isEmpty())
         result += " " + agenteUsuario;
      if (dependenciaDestinado != null && !dependenciaDestinado.trim().isEmpty())
         result += " " + dependenciaDestinado;
      if (usoDestinado != null && !usoDestinado.trim().isEmpty())
         result += " " + usoDestinado;
      if (tiponroprocedimiento != null && !tiponroprocedimiento.trim().isEmpty())
         result += " " + tiponroprocedimiento;
      if (formaAdquisicion != null && !formaAdquisicion.trim().isEmpty())
         result += " " + formaAdquisicion;
      if (nroOrdenCompra != null && !nroOrdenCompra.trim().isEmpty())
         result += " " + nroOrdenCompra;
      if (firmaArrendataria != null && !firmaArrendataria.trim().isEmpty())
         result += " " + firmaArrendataria;
      if (montoMensual != null)
         result += " " + montoMensual;
      if (titular != null && !titular.trim().isEmpty())
         result += " " + titular;
      return result;
   }
}