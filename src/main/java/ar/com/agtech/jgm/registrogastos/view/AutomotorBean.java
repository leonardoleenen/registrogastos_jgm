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

import ar.com.agtech.jgm.registrogastos.modelo.Automotor;

/**
 * Backing bean for Automotor entities.
 * <p>
 * This class provides CRUD functionality for all Automotor entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class AutomotorBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Automotor entities
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

   private Automotor automotor;

   public Automotor getAutomotor()
   {
      return this.automotor;
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
         this.automotor = this.search;
      }
      else
      {
         this.automotor = this.entityManager.find(Automotor.class, getId());
      }
   }

   /*
    * Support updating and deleting Automotor entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.automotor);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.automotor);
            return "view?faces-redirect=true&id=" + this.automotor.getId();
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
         this.entityManager.remove(this.entityManager.find(Automotor.class, getId()));
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
    * Support searching Automotor entities with pagination
    */

   private int page;
   private long count;
   private List<Automotor> pageItems;

   private Automotor search = new Automotor();

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

   public Automotor getSearch()
   {
      return this.search;
   }

   public void setSearch(Automotor search)
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
      Root<Automotor> root = countCriteria.from(Automotor.class);
      countCriteria = countCriteria.select(builder.count(root)).where(getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria).getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Automotor> criteria = builder.createQuery(Automotor.class);
      root = criteria.from(Automotor.class);
      TypedQuery<Automotor> query = this.entityManager.createQuery(criteria.select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Automotor> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String tipo = this.search.getTipo();
      if (tipo != null && !"".equals(tipo))
      {
         predicatesList.add(builder.like(root.<String> get("tipo"), '%' + tipo + '%'));
      }
      String marca = this.search.getMarca();
      if (marca != null && !"".equals(marca))
      {
         predicatesList.add(builder.like(root.<String> get("marca"), '%' + marca + '%'));
      }
      String modelo = this.search.getModelo();
      if (modelo != null && !"".equals(modelo))
      {
         predicatesList.add(builder.like(root.<String> get("modelo"), '%' + modelo + '%'));
      }
      int anio = this.search.getAnio();
      if (anio != 0)
      {
         predicatesList.add(builder.equal(root.get("anio"), anio));
      }
      String patente = this.search.getPatente();
      if (patente != null && !"".equals(patente))
      {
         predicatesList.add(builder.like(root.<String> get("patente"), '%' + patente + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Automotor> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Automotor entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Automotor> getAll()
   {

      CriteriaQuery<Automotor> criteria = this.entityManager.getCriteriaBuilder().createQuery(Automotor.class);
      return this.entityManager.createQuery(criteria.select(criteria.from(Automotor.class))).getResultList();
   }

   public Converter getConverter()
   {

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context, UIComponent component, String value)
         {

            return AutomotorBean.this.entityManager.find(Automotor.class, Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context, UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Automotor) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Automotor add = new Automotor();

   public Automotor getAdd()
   {
      return this.add;
   }

   public Automotor getAdded()
   {
      Automotor added = this.add;
      this.add = new Automotor();
      return added;
   }
}