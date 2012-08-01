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

import ar.com.agtech.jgm.registrogastos.modelo.Telefonia;

/**
 * Backing bean for Telefonia entities.
 * <p>
 * This class provides CRUD functionality for all Telefonia entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class TelefoniaBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Telefonia entities
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

   private Telefonia telefonia;

   public Telefonia getTelefonia()
   {
      return this.telefonia;
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
         this.telefonia = this.search;
      }
      else
      {
         this.telefonia = this.entityManager.find(Telefonia.class, getId());
      }
   }

   /*
    * Support updating and deleting Telefonia entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.telefonia);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.telefonia);
            return "view?faces-redirect=true&id=" + this.telefonia.getId();
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
         this.entityManager.remove(this.entityManager.find(Telefonia.class, getId()));
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
    * Support searching Telefonia entities with pagination
    */

   private int page;
   private long count;
   private List<Telefonia> pageItems;

   private Telefonia search = new Telefonia();

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

   public Telefonia getSearch()
   {
      return this.search;
   }

   public void setSearch(Telefonia search)
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
      Root<Telefonia> root = countCriteria.from(Telefonia.class);
      countCriteria = countCriteria.select(builder.count(root)).where(getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria).getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Telefonia> criteria = builder.createQuery(Telefonia.class);
      root = criteria.from(Telefonia.class);
      TypedQuery<Telefonia> query = this.entityManager.createQuery(criteria.select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Telefonia> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String tiponroprocedimiento = this.search.getTiponroprocedimiento();
      if (tiponroprocedimiento != null && !"".equals(tiponroprocedimiento))
      {
         predicatesList.add(builder.like(root.<String> get("tiponroprocedimiento"), '%' + tiponroprocedimiento + '%'));
      }
      String empresaPrestadora = this.search.getEmpresaPrestadora();
      if (empresaPrestadora != null && !"".equals(empresaPrestadora))
      {
         predicatesList.add(builder.like(root.<String> get("empresaPrestadora"), '%' + empresaPrestadora + '%'));
      }
      int montoServicio = this.search.getMontoServicio();
      if (montoServicio != 0)
      {
         predicatesList.add(builder.equal(root.get("montoServicio"), montoServicio));
      }
      String ubicacionGeograficaDependencia = this.search.getUbicacionGeograficaDependencia();
      if (ubicacionGeograficaDependencia != null && !"".equals(ubicacionGeograficaDependencia))
      {
         predicatesList.add(builder.like(root.<String> get("ubicacionGeograficaDependencia"), '%' + ubicacionGeograficaDependencia + '%'));
      }
      String nroOrdenCompra = this.search.getNroOrdenCompra();
      if (nroOrdenCompra != null && !"".equals(nroOrdenCompra))
      {
         predicatesList.add(builder.like(root.<String> get("nroOrdenCompra"), '%' + nroOrdenCompra + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Telefonia> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Telefonia entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Telefonia> getAll()
   {

      CriteriaQuery<Telefonia> criteria = this.entityManager.getCriteriaBuilder().createQuery(Telefonia.class);
      return this.entityManager.createQuery(criteria.select(criteria.from(Telefonia.class))).getResultList();
   }

   public Converter getConverter()
   {

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context, UIComponent component, String value)
         {

            return TelefoniaBean.this.entityManager.find(Telefonia.class, Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context, UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Telefonia) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Telefonia add = new Telefonia();

   public Telefonia getAdd()
   {
      return this.add;
   }

   public Telefonia getAdded()
   {
      Telefonia added = this.add;
      this.add = new Telefonia();
      return added;
   }
}