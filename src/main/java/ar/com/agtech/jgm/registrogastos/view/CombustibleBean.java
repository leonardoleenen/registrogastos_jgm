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

import ar.com.agtech.jgm.registrogastos.modelo.Combustible;

/**
 * Backing bean for Combustible entities.
 * <p>
 * This class provides CRUD functionality for all Combustible entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class CombustibleBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Combustible entities
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

   private Combustible combustible;

   public Combustible getCombustible()
   {
      return this.combustible;
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
         this.combustible = this.search;
      }
      else
      {
         this.combustible = this.entityManager.find(Combustible.class, getId());
      }
   }

   /*
    * Support updating and deleting Combustible entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.combustible);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.combustible);
            return "view?faces-redirect=true&id=" + this.combustible.getId();
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
         this.entityManager.remove(this.entityManager.find(Combustible.class, getId()));
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
    * Support searching Combustible entities with pagination
    */

   private int page;
   private long count;
   private List<Combustible> pageItems;

   private Combustible search = new Combustible();

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

   public Combustible getSearch()
   {
      return this.search;
   }

   public void setSearch(Combustible search)
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
      Root<Combustible> root = countCriteria.from(Combustible.class);
      countCriteria = countCriteria.select(builder.count(root)).where(getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria).getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Combustible> criteria = builder.createQuery(Combustible.class);
      root = criteria.from(Combustible.class);
      TypedQuery<Combustible> query = this.entityManager.createQuery(criteria.select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Combustible> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String tipoNroProcedimiento = this.search.getTipoNroProcedimiento();
      if (tipoNroProcedimiento != null && !"".equals(tipoNroProcedimiento))
      {
         predicatesList.add(builder.like(root.<String> get("tipoNroProcedimiento"), '%' + tipoNroProcedimiento + '%'));
      }
      String razonSocialProveedor = this.search.getRazonSocialProveedor();
      if (razonSocialProveedor != null && !"".equals(razonSocialProveedor))
      {
         predicatesList.add(builder.like(root.<String> get("razonSocialProveedor"), '%' + razonSocialProveedor + '%'));
      }
      String cuitProveedor = this.search.getCuitProveedor();
      if (cuitProveedor != null && !"".equals(cuitProveedor))
      {
         predicatesList.add(builder.like(root.<String> get("cuitProveedor"), '%' + cuitProveedor + '%'));
      }
      String nroOrdenCompra = this.search.getNroOrdenCompra();
      if (nroOrdenCompra != null && !"".equals(nroOrdenCompra))
      {
         predicatesList.add(builder.like(root.<String> get("nroOrdenCompra"), '%' + nroOrdenCompra + '%'));
      }
      int cantLitrosPorMes = this.search.getCantLitrosPorMes();
      if (cantLitrosPorMes != 0)
      {
         predicatesList.add(builder.equal(root.get("cantLitrosPorMes"), cantLitrosPorMes));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Combustible> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Combustible entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Combustible> getAll()
   {

      CriteriaQuery<Combustible> criteria = this.entityManager.getCriteriaBuilder().createQuery(Combustible.class);
      return this.entityManager.createQuery(criteria.select(criteria.from(Combustible.class))).getResultList();
   }

   public Converter getConverter()
   {

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context, UIComponent component, String value)
         {

            return CombustibleBean.this.entityManager.find(Combustible.class, Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context, UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Combustible) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Combustible add = new Combustible();

   public Combustible getAdd()
   {
      return this.add;
   }

   public Combustible getAdded()
   {
      Combustible added = this.add;
      this.add = new Combustible();
      return added;
   }
}