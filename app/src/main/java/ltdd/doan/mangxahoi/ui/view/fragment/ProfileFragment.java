package ltdd.doan.mangxahoi.ui.view.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.databinding.FragmentPostDetailsBinding;
import ltdd.doan.mangxahoi.databinding.FragmentProfileBinding;
import ltdd.doan.mangxahoi.session.Session;
import ltdd.doan.mangxahoi.ui.view.adapter.PostAdapterProfile;
import ltdd.doan.mangxahoi.ui.viewmodel.ProfileViewModel;
@AndroidEntryPoint
public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        binding.setProfileFragment(this);

        Bundle bundle = getArguments();
        int user_id = (bundle == null ) ? 1 : bundle.getInt("user_id");

        binding.frgProfileSwipeRefresh.setOnRefreshListener(() -> {
            mViewModel.getUserDetailsById(user_id);
            mViewModel.getPostsByUserId(user_id);

            binding.frgProfileSwipeRefresh.setRefreshing(false);
        });

        mViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            binding.setUser(user);
            // TODO: 4/18/2023 ảnh
        });

        mViewModel.getPosts().observe(getViewLifecycleOwner(), posts -> {
            binding.setPostCount(posts.size());

            PostAdapterProfile postAdapter = new PostAdapterProfile(requireContext(), posts);
            binding.setPostAdapter(postAdapter);
            binding.frgProfileRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        });

        mViewModel.getUserDetailsById(user_id);
        mViewModel.getPostsByUserId(user_id);

        return binding.getRoot();
    }

    // TODO: 4/18/2023
    public void navToFollow(View view, List<User> users) {
    }

    public void follow(User user) {
        mViewModel.follow(user.getId());

        // update ui
        mViewModel.getUserDetailsById(user.getId());
    }

    public void unfollow(int user_id) {
        mViewModel.unfollow(user_id);

        // update ui
        mViewModel.getUserDetailsById(user_id);
    }

}