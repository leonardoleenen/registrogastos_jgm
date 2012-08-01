package ar.com.agtech.jgm.registrogastos.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ar.com.agtech.jgm.registrogastos.modelo.Pasaje;

/**
 * Backing bean for Pasaje entities.
 * <p>
 * This class provides CRUD functionality for all Pasaje entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class PasajeBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Pasaje entities
    */

   private Long id;

   public Long getId()
   {
      return this.id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   private Pasaje pasaje;

   public Pasaje getPasaje()
   {
      return this.pasaje;
   }

   @Inject
   private Conversation conversation;

   @PersistenceContext(type = PersistenceContextType.EXTENDED)
   private EntityManager entityManager;

   public String create()
   {

      this.conversation.begin();
      return "create?faces-redirect=true";
   }

   public void retrieve()
   {

      if (FacesContext.getCurrentInstance().isPostback())
      {
         return;
      }

      if (this.conversation.isTransient())
      {
         this.conversation.begin();
      }

      if (this.id == null)
      {
         this.pasaje = this.search;
      }
      else
      {
         this.pasaje = this.entityManager.find(Pasaje.class, getId());
      }
   }

   /*
    * Support updating and deleting Pasaje entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.pasaje);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.pasaje);
            return "view?faces-redirect=true&id=" + this.pasaje.getId();
         }
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   public String delete()
   {
      this.conversation.end();

      try
      {
         this.entityManager.remove(this.entityManager.find(Pasaje.class, getId()));
         this.entityManager.flush();
         return "search?faces-redirect=true";
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   /*
    * Support searching Pasaje entities with pagination
    */

   private int page;
   private long count;
   private List<Pasaje> pageItems;

   private Pasaje search = new Pasaje();

   public int getPage()
   {
      return this.page;
   }

   public void setPage(int page)
   {
      this.page = page;
   }

   public int getPageSize()
   {
      return 10;
   }

   public Pasaje getSearch()
   {
      return this.search;
   }

   public void setSearch(Pasaje search)
   {
      this.search = search;
   }

   public void search()
   {
      this.page = 0;
   }

   public void paginate()
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

      // Populate this.count

      CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
      Root<Pasaje> root = countCriteria.from(Pasaje.class);
      countCriteria = countCriteria.select(builder.count(root)).where(getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria).getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Pasaje> criteria = builder.createQuery(Pasaje.class);
      root = criteria.from(Pasaje.class);
      TypedQuery<Pasaje> query = this.entityManager.createQuery(criteria.select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Pasaje> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      int cantPasajesContratadosUltimoAnio = this.search.getCantPasajesContratadosUltimoAnio();
      if (cantPasajesContratadosUltimoAnio != 0)
      {
         predicatesList.add(builder.equal(root.get("cantPasajesContratadosUltimoAnio"), cantPasajesContratadosUltimoAnio));
      }
      String destinoDePasajes = this.search.getDestinoDePasajes();
      if (destinoDePasajes != null && !"".equals(destinoDePasajes))
      {
         predicatesList.add(builder.like(root.<String> get("destinoDePasajes"), '%' + destinoDePasajes + '%'));
      }
      String empresaAerea = this.search.getEmpresaAerea();
      if (empresaAerea != null && !"".equals(empresaAerea))
      {
         predicatesList.add(builder.like(root.<String> get("empresaAerea"), '%' + empresaAerea + '%'));
      }
      String agenciaTurismo = this.search.getAgenciaTurismo();
      if (agenciaTurismo != null && !"".equals(agenciaTurismo))
      {
         predicatesList.add(builder.like(root.<String> get("agenciaTurismo"), '%' + agenciaTurismo + '%'));
      }
      int montoPasaje = this.search.getMontoPasaje();
      if (montoPasaje != 0)
      {
         predicatesList.add(builder.equal(root.get("montoPasaje"), montoPasaje));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Pasaje> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Pasaje entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Pasaje> getAll()
   {

      CriteriaQuery<Pasaje> criteria = this.entityManager.getCriteriaBuilder().createQuery(Pasaje.class);
      return this.entityManager.createQuery(criteria.select(criteria.from(Pasaje.class))).getResultList();
   }

   public Converter getConverter()
   {

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context, UIComponent component, String value)
         {

            return PasajeBean.this.entityManager.find(Pasaje.class, Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context, UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Pasaje) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Pasaje add = new Pasaje();

   public Pasaje getAdd()
   {
      return this.add;
   }

   public Pasaje getAdded()
   {
      Pasaje added = this.add;
      this.add = new Pasaje();
      return added;
   }
}