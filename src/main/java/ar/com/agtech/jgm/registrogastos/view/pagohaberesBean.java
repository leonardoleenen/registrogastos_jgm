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

import ar.com.agtech.jgm.registrogastos.modelo.pagohaberes;

/**
 * Backing bean for pagohaberes entities.
 * <p>
 * This class provides CRUD functionality for all pagohaberes entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class pagohaberesBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving pagohaberes entities
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

   private pagohaberes pagohaberes;

   public pagohaberes getpagohaberes()
   {
      return this.pagohaberes;
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
         this.pagohaberes = this.search;
      }
      else
      {
         this.pagohaberes = this.entityManager.find(pagohaberes.class, getId());
      }
   }

   /*
    * Support updating and deleting pagohaberes entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.pagohaberes);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.pagohaberes);
            return "view?faces-redirect=true&id=" + this.pagohaberes.getId();
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
         this.entityManager.remove(this.entityManager.find(pagohaberes.class, getId()));
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
    * Support searching pagohaberes entities with pagination
    */

   private int page;
   private long count;
   private List<pagohaberes> pageItems;

   private pagohaberes search = new pagohaberes();

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

   public pagohaberes getSearch()
   {
      return this.search;
   }

   public void setSearch(pagohaberes search)
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
      Root<pagohaberes> root = countCriteria.from(pagohaberes.class);
      countCriteria = countCriteria.select(builder.count(root)).where(getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria).getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<pagohaberes> criteria = builder.createQuery(pagohaberes.class);
      root = criteria.from(pagohaberes.class);
      TypedQuery<pagohaberes> query = this.entityManager.createQuery(criteria.select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<pagohaberes> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String tiponroprocedimiento = this.search.getTiponroprocedimiento();
      if (tiponroprocedimiento != null && !"".equals(tiponroprocedimiento))
      {
         predicatesList.add(builder.like(root.<String> get("tiponroprocedimiento"), '%' + tiponroprocedimiento + '%'));
      }
      String ordencompra = this.search.getOrdencompra();
      if (ordencompra != null && !"".equals(ordencompra))
      {
         predicatesList.add(builder.like(root.<String> get("ordencompra"), '%' + ordencompra + '%'));
      }
      int cantperCobraHaberesPorBanco = this.search.getCantperCobraHaberesPorBanco();
      if (cantperCobraHaberesPorBanco != 0)
      {
         predicatesList.add(builder.equal(root.get("cantperCobraHaberesPorBanco"), cantperCobraHaberesPorBanco));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<pagohaberes> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back pagohaberes entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<pagohaberes> getAll()
   {

      CriteriaQuery<pagohaberes> criteria = this.entityManager.getCriteriaBuilder().createQuery(pagohaberes.class);
      return this.entityManager.createQuery(criteria.select(criteria.from(pagohaberes.class))).getResultList();
   }

   public Converter getConverter()
   {

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context, UIComponent component, String value)
         {

            return pagohaberesBean.this.entityManager.find(pagohaberes.class, Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context, UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((pagohaberes) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private pagohaberes add = new pagohaberes();

   public pagohaberes getAdd()
   {
      return this.add;
   }

   public pagohaberes getAdded()
   {
      pagohaberes added = this.add;
      this.add = new pagohaberes();
      return added;
   }
}