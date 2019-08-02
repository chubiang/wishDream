package kr.co.wishDream.repository;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.query.Criteria;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.wishDream.domain.Member;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@Transactional
public class MemberRepository implements R2dbcRepository<Member, String> {

	@Autowired
	private DatabaseClient client;

	@Override
	public Mono<Member> findById(String id) {
		return client.select()
				.from(Member.class)
				.matching(Criteria.where("email").is(id))
				.as(Member.class).one();
	}

	@Override
	public Mono<Member> findById(Publisher<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Member> Mono<S> save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Member> Flux<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Member> Flux<S> saveAll(Publisher<S> entityStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Boolean> existsById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Boolean> existsById(Publisher<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Member> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Member> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Member> findAllById(Publisher<String> idStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Long> count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(Publisher<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> delete(Member entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Iterable<? extends Member> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Publisher<? extends Member> entityStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
