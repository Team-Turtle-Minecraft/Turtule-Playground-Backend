package org.turtle.minecraft_service.repository.secondary;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.turtle.minecraft_service.domain.secondary.user.MinecraftUser;

import java.util.List;


public interface MinecraftRepository extends JpaRepository<MinecraftUser, Long> {

   MinecraftUser findByPlayerName(String nickname);

   @Query(value =
           "WITH ranked_users AS ( " +
                   "SELECT *, " +
                   "RANK() OVER (ORDER BY progress DESC) as rank_num " +
                   "FROM user_detailed_info  " +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5",
           nativeQuery = true)
   List<MinecraftUser> findTop5UserWithCollection();


   @Query(value =
           "WITH ranked_users AS (" +
                   "SELECT *, " +
                   "    RANK() OVER (ORDER BY money DESC) as rank_num " +
                   "    FROM user_detailed_info " +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5",
           nativeQuery = true)
   List<MinecraftUser> findTop5UserWithMoney();

   @Query(value =
           "WITH ranked_users AS ( " +
                   "    SELECT *, " +
                   "    DENSE_RANK() OVER (ORDER BY character_level DESC) as rank_num " +
                   "    FROM user_detailed_info " +
                   "    WHERE character_class = :jobName " +
                   ") " +
                   "SELECT * FROM ranked_users " +
                   "WHERE rank_num <= 5",
           nativeQuery = true)
   List<MinecraftUser> findTop5UserWithCombatJobLevel(@Param("jobName") String jobName);

   @Query(value =
           "WITH ranked_users AS (" +
                   "    SELECT *, " +
                   "    DENSE_RANK() OVER (ORDER BY fisher_level DESC) as rank_num " +
                   "    FROM user_detailed_info" +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5",
           nativeQuery = true
   )
   List<MinecraftUser> findTop5UserWithFishingLevel();


   @Query(value =
           "WITH ranked_users AS (" +
                   "    SELECT *, " +
                   "    DENSE_RANK() OVER (ORDER BY farming_level DESC) as rank_num " +
                   "    FROM user_detailed_info" +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5",
           nativeQuery = true
   )
   List<MinecraftUser> findTop5UserWithFarmingLevel();


   @Query(value =
           "WITH ranked_users AS (" +
                   "    SELECT *, " +
                   "    DENSE_RANK() OVER (ORDER BY mining_level DESC) as rank_num " +
                   "    FROM user_detailed_info" +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5",
           nativeQuery = true
   )
   List<MinecraftUser> findTop5UserWithMiningLevel();



   @Query(value =
           "WITH ranked_users AS (" +
                   "    SELECT *, " +
                   "    DENSE_RANK() OVER (ORDER BY smithing_level DESC) as rank_num " +
                   "    FROM user_detailed_info" +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5",
           nativeQuery = true
   )
   List<MinecraftUser> findTop5UserWithSmithingLevel();



   @Query(value =
           "WITH ranked_users AS (" +
                   "    SELECT *, " +
                   "    DENSE_RANK() OVER (ORDER BY cooking_level DESC) as rank_num " +
                   "    FROM user_detailed_info" +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5",
           nativeQuery = true
   )
   List<MinecraftUser> findTop5UserWithCookingLevel();

}
