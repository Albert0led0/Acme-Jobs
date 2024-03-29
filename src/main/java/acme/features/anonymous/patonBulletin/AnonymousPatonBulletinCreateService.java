
package acme.features.anonymous.patonBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.PatonBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousPatonBulletinCreateService implements AbstractCreateService<Anonymous, PatonBulletin> {

	@Autowired
	private AnonymousPatonBulletinRepository patonRepository;


	@Override
	public boolean authorise(final Request<PatonBulletin> request) {
		assert request != null;

		return true;

	}

	@Override
	public PatonBulletin instantiate(final Request<PatonBulletin> request) {
		assert request != null;

		PatonBulletin result;
		Date moment = new Date(System.currentTimeMillis() - 1);

		result = new PatonBulletin();
		result.setAuthor("Miguel Patón");
		result.setText("Initial text");
		result.setMoment(moment);
		result.setCompany("Initial company");

		return result;

	}

	@Override
	public void unbind(final Request<PatonBulletin> request, final PatonBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment", "company");

	}

	@Override
	public void bind(final Request<PatonBulletin> request, final PatonBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void validate(final Request<PatonBulletin> request, final PatonBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<PatonBulletin> request, final PatonBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);

		entity.setMoment(moment);

		this.patonRepository.save(entity);
	}
}
